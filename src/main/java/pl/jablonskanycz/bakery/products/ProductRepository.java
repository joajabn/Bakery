package pl.jablonskanycz.bakery.products;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findByName(String name);
    List<Product> findByType(ProductType productType);
    void addProduct(Product productToAdd);
    void deleteProduct(Product productToRemove);


}
