package pl.jablonskanycz.bakery.products;

public interface ProductFactory {
  Product bake(String name, double price, ProductType productType);
}
