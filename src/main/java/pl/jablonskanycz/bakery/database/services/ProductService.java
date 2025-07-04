package pl.jablonskanycz.bakery.database.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jablonskanycz.bakery.database.domain.ProductEntity;
import pl.jablonskanycz.bakery.database.domain.ProductType;
import pl.jablonskanycz.bakery.database.exceptions.ProductNotFoundException;
import pl.jablonskanycz.bakery.database.mapper.ProductMapper;
import pl.jablonskanycz.bakery.database.models.ProductModel;
import pl.jablonskanycz.bakery.database.repositories.ProductRepository;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public List<ProductModel> getAllProducts() {
        log.info("Getting all products");
        List<ProductModel> allProducts = productRepository.findAll().stream()
                .map(productMapper::toModel)
                .collect(Collectors.toList());
        log.info("Getting all products completed");
        return allProducts;
    }

    public List<ProductModel> getAllBuns() {
        log.info("Getting all buns");
        List<ProductModel> allBuns = productRepository.findAll().stream()
                .filter(product -> product.getProductType().equals(ProductType.BUN))
                .map(productMapper::toModel)
                .collect(Collectors.toList());
        log.info("Getting all buns completed");
        return allBuns;
    }
    public List<ProductModel> getAllBreads() {
        log.info("Getting all breads");
        List<ProductModel> allBreads = productRepository.findAll().stream()
                .filter(product -> product.getProductType().equals(ProductType.BREAD))
                .map(productMapper::toModel)
                .collect(Collectors.toList());
        log.info("Getting all breads completed");
        return allBreads;
    }

    public ProductModel findById(long productId) {
        log.info("Getting product with ID: {}", productId);
        ProductModel productModel = productRepository.findById(productId)
                .map(productMapper::toModel)
                .orElseThrow(handleProductNotFound("Product with given ID does not exist"));
        log.info("Getting product with given ID completed");
        return productModel;
    }

    @Transactional
    public ProductModel addProduct(ProductModel productModel) {
        ProductEntity saved = productRepository.save(productMapper.toEntity(productModel));
        log.info("Added new product with ID: {}", saved.getProductId());
        return productMapper.toModel(saved);
    }

    @Transactional
    public ProductModel updateProduct(long productToUpdateId, ProductModel productModelToUpdate) {
        log.info("Updating product with ID: {}", productToUpdateId);
        ProductEntity productToUpdate = returnProductIfExists(productToUpdateId);
        productToUpdate.setProductName(productModelToUpdate.getProductName());
        productToUpdate.setPrice(productModelToUpdate.getPrice());
        productToUpdate.setProductType(productModelToUpdate.getProductType());
        log.info("Updating product completed");
        return productMapper.toModel(productToUpdate);
    }

    public void deleteProduct(long productToDeleteId) {
        log.info("Deleting product with ID: {}", productToDeleteId);
        productRepository.delete(returnProductIfExists(productToDeleteId));
        log.info("Product with ID: {} was deleted successfully", productToDeleteId);
    }

    private ProductEntity returnProductIfExists(long productToUpdateId) {
        return productRepository.findById(productToUpdateId).orElseThrow(handleProductNotFound("Product not found"));
    }

    private static Supplier<ProductNotFoundException> handleProductNotFound(String message) {
        return () -> {
            log.warn(message);
            return new ProductNotFoundException(message);
        };
    }


}
