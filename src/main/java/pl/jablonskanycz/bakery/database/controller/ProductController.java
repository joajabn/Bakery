package pl.jablonskanycz.bakery.database.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jablonskanycz.bakery.database.dto.ProductDTO;
import pl.jablonskanycz.bakery.database.exceptions.ProductNotFoundException;
import pl.jablonskanycz.bakery.database.mapper.ProductMapper;
import pl.jablonskanycz.bakery.database.models.ProductModel;
import pl.jablonskanycz.bakery.database.services.ProductService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bakery/products")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> allProducts = productService.getAllProducts().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/buns")
    public ResponseEntity<List<ProductDTO>> getAllBuns() {
        List<ProductDTO> allBuns = productService.getAllBuns().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allBuns, HttpStatus.OK);
    }

    @GetMapping("/breads")
    public ResponseEntity<List<ProductDTO>> getAllBreads() {
        List<ProductDTO> allBreads = productService.getAllBreads().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allBreads, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productToAdd) {
        ProductModel newProduct = productService.addProduct(mapper.toModel(productToAdd));
        return ResponseEntity.created((URI.create("/bakery/products/" + newProduct.getProductId())))
                .body(mapper.toDTO(newProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable("id") long productIdToUpdate, @RequestBody ProductDTO productToUpdate) {
        ProductModel updatedProduct = productService.updateProduct(productIdToUpdate, mapper.toModel(productToUpdate));
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long productToDeleteId) {
        productService.deleteProduct(productToDeleteId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Void> handleProductNotFound(ProductNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.notFound().build();
    }

}
