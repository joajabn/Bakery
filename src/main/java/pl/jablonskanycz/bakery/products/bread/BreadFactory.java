package pl.jablonskanycz.bakery.products.bread;

import pl.jablonskanycz.bakery.products.ProductType;
import pl.jablonskanycz.bakery.products.bread.Bread;

public interface BreadFactory {
    Bread bakeBread(String name, double price, ProductType productType);
}
