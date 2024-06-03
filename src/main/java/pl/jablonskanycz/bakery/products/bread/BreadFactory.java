package pl.jablonskanycz.bakery.products.bread;

import pl.jablonskanycz.bakery.products.ProductFactory;
import pl.jablonskanycz.bakery.products.ProductType;
import pl.jablonskanycz.bakery.products.bread.Bread;

public interface BreadFactory extends ProductFactory {
    Bread bake(String name, double price, ProductType productType);
}
