package pl.jablonskanycz.bakery.filebasedrepositories.products;

public interface ProductFactory {
  Product bake(String name, double price, ProductType productType);
}
