package pl.jablonskanycz.bakery.filebasedrepositories.products.bread;

import pl.jablonskanycz.bakery.filebasedrepositories.products.ProductFactory;
import pl.jablonskanycz.bakery.filebasedrepositories.products.ProductType;

public class PlainGrainBreadFactory implements ProductFactory {

    @Override
    public Bread bake(String name, double price, ProductType productType) {
        return new Bread(name, price, productType);
    }
}
