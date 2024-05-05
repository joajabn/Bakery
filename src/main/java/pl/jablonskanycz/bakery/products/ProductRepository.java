package pl.jablonskanycz.bakery.products;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findByName(String name);
    void addProduct(Product productToAdd);
    void deleteProduct(Product productToRemove);


}
