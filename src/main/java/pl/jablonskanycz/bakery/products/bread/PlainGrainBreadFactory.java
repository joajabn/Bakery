package pl.jablonskanycz.bakery.products.bread;

import pl.jablonskanycz.bakery.products.ProductFactory;
import pl.jablonskanycz.bakery.products.ProductType;

public class PlainGrainBreadFactory implements ProductFactory {

    @Override
    public Bread bake(String name, double price, ProductType productType) {
        return new Bread(name, price, productType);
    }
}
