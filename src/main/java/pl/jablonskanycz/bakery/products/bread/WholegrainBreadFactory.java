package pl.jablonskanycz.bakery.products.bread;

import pl.jablonskanycz.bakery.products.ProductFactory;
import pl.jablonskanycz.bakery.products.ProductType;
import pl.jablonskanycz.bakery.products.bread.Bread;
import pl.jablonskanycz.bakery.products.bread.BreadFactory;

public class WholegrainBreadFactory implements ProductFactory {


    @Override
    public Bread bake(String name, double price, ProductType productType) {
        return new Bread(name, price, productType);
    }
}
