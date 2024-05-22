package pl.jablonskanycz.bakery.products.bun;

import pl.jablonskanycz.bakery.products.ProductFactory;
import pl.jablonskanycz.bakery.products.ProductType;

public interface BunFactory extends ProductFactory {
    Bun bake(String name, double price, ProductType productType);
}
