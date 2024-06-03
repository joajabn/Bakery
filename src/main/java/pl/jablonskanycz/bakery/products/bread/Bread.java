package pl.jablonskanycz.bakery.products.bread;

import lombok.AllArgsConstructor;
import pl.jablonskanycz.bakery.products.Product;
import pl.jablonskanycz.bakery.products.ProductType;

public class Bread extends Product {

    public Bread(String name, double price, ProductType productType) {
        super(name, price, productType);
    }
}
