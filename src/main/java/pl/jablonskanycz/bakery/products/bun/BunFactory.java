package pl.jablonskanycz.bakery.products.bun;

import pl.jablonskanycz.bakery.products.ProductType;

public interface BunFactory {
    Bun bakeBun(String name, double price, ProductType productType);
}
