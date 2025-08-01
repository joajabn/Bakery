package pl.jablonskanycz.bakery.database.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jablonskanycz.bakery.database.domain.ProductTypeEntity;
import pl.jablonskanycz.bakery.database.exceptions.ProductNotFoundException;
import pl.jablonskanycz.bakery.database.mapper.ProductMapper;
import pl.jablonskanycz.bakery.database.models.ProductModel;
import pl.jablonskanycz.bakery.database.repositories.ProductRepository;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ProductServiceIntegrationTest {
    public static final String BREAD = "BREAD";
    public static final String BUN = "BUN";

    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    private ProductModel sampleProduct;
    private ProductTypeEntity productTypeBread = ProductTypeEntity.builder()
        .productType(BREAD)
        .build();
    private ProductTypeEntity productTypeBun = ProductTypeEntity.builder()
        .productType(BUN)
        .build();

    @BeforeEach
    public void setUp() throws SQLException {
        productService = new ProductService(productRepository, productMapper);

        sampleProduct = ProductModel.builder()
                .productName("Test Bread")
                .price(2.5)
                .productType(productTypeBread)
                .build();
// for connecting with test H2 database
//        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
    }

    @Test
    public void shouldAddProduct() {
        //given
        List<ProductModel> allProductsBefore = productService.getAllProducts();
        long countBefore = allProductsBefore.stream()
                .filter(p -> "Test Bread".equals(p.getProductName()))
                .count();

        //when
        ProductModel addedProduct = productService.addProduct(sampleProduct);

        //then
        List<ProductModel> allProductsAfter = productService.getAllProducts();
        long countAfter = allProductsAfter.stream()
                .filter(p -> "Test Bread".equals(p.getProductName()))
                .count();

        assertEquals(countBefore + 1, countAfter);
        assertNotNull(addedProduct.getProductId());
        assertEquals("Test Bread", addedProduct.getProductName());
        assertEquals(BREAD, addedProduct.getProductType().getProductType());
    }

    @Test
    public void shouldFindProductById() {
        //given
        ProductModel addedProduct = productService.addProduct(sampleProduct);

        //when
        ProductModel foundProduct = productService.findById(addedProduct.getProductId());

        //then
        assertNotNull(foundProduct);
        assertEquals(addedProduct.getProductId(), foundProduct.getProductId());
        assertEquals("Test Bread", foundProduct.getProductName());
    }

    @Test
    public void shouldThrowExceptionWhenProductNotFound() {
        //given
        long notExistingId = 10L;

        //when then
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.findById(notExistingId);
        });

        assertTrue(exception.getMessage().contains("Product with given ID does not exist"));
    }

    @Test
    public void shouldUpdateProduct() {
        //given
        ProductModel addedProduct = productService.addProduct(sampleProduct);
        ProductModel updateInfo = ProductModel.builder()
                .productName("Updated Bread")
                .price(3.0)
                .productType(productTypeBread)
                .build();

        //when
        ProductModel updatedProduct = productService.updateProduct(addedProduct.getProductId(), updateInfo);

        //then
        assertEquals("Updated Bread", updatedProduct.getProductName());
        assertEquals(3.0, updatedProduct.getPrice());
        assertEquals(BREAD, updatedProduct.getProductType().getProductType());

        // Verify persistence
        ProductModel foundProduct = productService.findById(addedProduct.getProductId());
        assertEquals("Updated Bread", foundProduct.getProductName());
    }

    @Test
    public void shouldDeleteProduct() {
        //given
        ProductModel addedProduct = productService.addProduct(sampleProduct);
        long productId = addedProduct.getProductId();

        //when
        productService.deleteProduct(productId);

        //then
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.findById(productId);
        });
        assertTrue(exception.getMessage().contains("Product with given ID does not exist"));
    }

    @Test
    public void shouldGetAllBuns() {
        //given
        ProductModel bun1 = ProductModel.builder()
                .productName("Bun 1")
                .price(1.0)
                .productType(productTypeBun)
                .build();

        ProductModel bread = ProductModel.builder()
                .productName("Bread 1")
                .price(2.0)
                .productType(productTypeBread)
                .build();


        productService.addProduct(bun1);
        productService.addProduct(bread);

        //when
        List<ProductModel> buns = productService.getAllBuns();

        //then
        assertFalse(buns.isEmpty());
        assertTrue(buns.stream().allMatch(p -> BUN.equalsIgnoreCase(p.getProductType().getProductType())));
        assertTrue(buns.stream().anyMatch(p -> "Bun 1".equals(p.getProductName())));
    }

    @Test
    public void shouldGetAllBreads() {
        //given
        ProductModel bun = ProductModel.builder()
                .productName("Bun 2")
                .price(1.5)
                .productType(productTypeBun)
                .build();

        ProductModel bread1 = ProductModel.builder()
                .productName("Bread 2")
                .price(2.5)
                .productType(productTypeBread)
                .build();

        productService.addProduct(bun);
        productService.addProduct(bread1);

        //when
        List<ProductModel> breads = productService.getAllBreads();

        //then
        assertFalse(breads.isEmpty());
        assertTrue(breads.stream().allMatch(p -> BREAD.equalsIgnoreCase(p.getProductType().getProductType())));

    }
}