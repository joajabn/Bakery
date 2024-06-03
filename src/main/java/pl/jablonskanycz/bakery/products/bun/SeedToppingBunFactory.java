package pl.jablonskanycz.bakery.products.bun;

import pl.jablonskanycz.bakery.products.ProductFactory;
import pl.jablonskanycz.bakery.products.ProductType;

public class SeedToppingBunFactory implements ProductFactory {


    @Override
    public Bun bake(String name, double price, ProductType productType) {
        return new Bun(name, price, productType);
    }
}
