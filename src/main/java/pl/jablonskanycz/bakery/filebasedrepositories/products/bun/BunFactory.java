package pl.jablonskanycz.bakery.filebasedrepositories.products.bun;

import pl.jablonskanycz.bakery.filebasedrepositories.products.ProductFactory;
import pl.jablonskanycz.bakery.filebasedrepositories.products.ProductType;

public interface BunFactory extends ProductFactory {
    Bun bake(String name, double price, ProductType productType);
}
