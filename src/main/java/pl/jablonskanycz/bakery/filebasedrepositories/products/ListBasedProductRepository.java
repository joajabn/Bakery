package pl.jablonskanycz.bakery.filebasedrepositories.products;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ListBasedProductRepository implements ProductRepository {
    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product findByName(String name) {
        return products.stream()
                .filter(product -> name.equals(product.getName()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No such product"));
    }

    @Override
    public List<Product> findByType(ProductType type) {
        return products.stream()
                .filter(product -> type.equals(product.getProductType()))
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(Product productToAdd) {
        products.add(productToAdd);
    }

    @Override
    public void deleteProduct(Product bread) {
        if (!products.isEmpty()) {
            products.remove(bread);
        } else {
            throw new NoSuchElementException("Nothing to delete - list of bakery products is empty!");
        }
    }
}
