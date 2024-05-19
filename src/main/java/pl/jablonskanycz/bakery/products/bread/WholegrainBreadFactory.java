package pl.jablonskanycz.bakery.products.bread;

import pl.jablonskanycz.bakery.products.ProductType;
import pl.jablonskanycz.bakery.products.bread.Bread;
import pl.jablonskanycz.bakery.products.bread.BreadFactory;

public class WholegrainBreadFactory implements BreadFactory {


    @Override
    public Bread bakeBread(String name, double price, ProductType productType) {
        return new Bread(name, price, productType);
    }
}