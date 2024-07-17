package pl.jablonskanycz.bakery.filebasedrepositories.products.bun;

import pl.jablonskanycz.bakery.filebasedrepositories.products.Product;
import pl.jablonskanycz.bakery.filebasedrepositories.products.ProductType;

public class Bun extends Product {
    public Bun(String name, double price, ProductType productType) {
        super(name, price, productType);
    }

}
