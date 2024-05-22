package pl.jablonskanycz.bakery.products.bread;

import pl.jablonskanycz.bakery.products.ProductType;

public class PlainGrainBreadFactory implements BreadFactory {

    @Override
    public Bread bake(String name, double price, ProductType productType) {
        return new Bread(name, price, productType);
    }
}
