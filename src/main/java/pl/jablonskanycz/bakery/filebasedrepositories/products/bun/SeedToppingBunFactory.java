package pl.jablonskanycz.bakery.filebasedrepositories.products.bun;

import pl.jablonskanycz.bakery.filebasedrepositories.products.ProductFactory;
import pl.jablonskanycz.bakery.filebasedrepositories.products.ProductType;

public class SeedToppingBunFactory implements ProductFactory {


    @Override
    public Bun bake(String name, double price, ProductType productType) {
        return new Bun(name, price, productType);
    }
}
