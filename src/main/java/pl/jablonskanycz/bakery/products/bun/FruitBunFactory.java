package pl.jablonskanycz.bakery.products.bun;

import pl.jablonskanycz.bakery.products.ProductType;

public class FruitBunFactory implements BunFactory {


    @Override
    public Bun bakeBun(String name, double price, ProductType productType) {
        return new Bun(name, price, productType);
    }
}
