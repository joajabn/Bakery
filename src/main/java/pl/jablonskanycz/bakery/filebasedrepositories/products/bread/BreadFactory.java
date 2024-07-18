package pl.jablonskanycz.bakery.filebasedrepositories.products.bread;

import pl.jablonskanycz.bakery.filebasedrepositories.products.ProductFactory;
import pl.jablonskanycz.bakery.filebasedrepositories.products.ProductType;

public interface BreadFactory extends ProductFactory {
    Bread bake(String name, double price, ProductType productType);
}
