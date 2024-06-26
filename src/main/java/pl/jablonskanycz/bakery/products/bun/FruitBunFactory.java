package pl.jablonskanycz.bakery.products.bun;

import static pl.jablonskanycz.bakery.products.ProductType.FRUIT_BUN;

import pl.jablonskanycz.bakery.products.ProductFactory;
import pl.jablonskanycz.bakery.products.ProductType;

public class FruitBunFactory implements ProductFactory {

    @Override
    public Bun bake(String name, double price, ProductType productType) {
        return new Bun(name, price, FRUIT_BUN);
    }
}
