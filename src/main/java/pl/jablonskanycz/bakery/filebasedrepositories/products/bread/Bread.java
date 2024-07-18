package pl.jablonskanycz.bakery.filebasedrepositories.products.bread;

import pl.jablonskanycz.bakery.filebasedrepositories.products.ProductType;
import pl.jablonskanycz.bakery.filebasedrepositories.products.Product;

public class Bread extends Product {

    public Bread(String name, double price, ProductType productType) {
        super(name, price, productType);
    }
}
