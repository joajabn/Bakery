package pl.jablonskanycz.bakery.products;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pl.jablonskanycz.bakery.products.bread.PlainGrainBreadFactory;
import pl.jablonskanycz.bakery.products.bread.SeedToppingBreadFactory;
import pl.jablonskanycz.bakery.products.bread.WholegrainBreadFactory;
import pl.jablonskanycz.bakery.products.bun.FruitBunFactory;
import pl.jablonskanycz.bakery.products.bun.SeedToppingBunFactory;
import pl.jablonskanycz.bakery.products.bun.VeggieBunFactory;

import static pl.jablonskanycz.bakery.products.ProductType.*;

public class FiledBasedProductRepository implements ProductRepository {

    private static final Path productPath = Path.of("src", "main", "resources", "PRODUCT.csv");
    private Map<ProductType, ProductFactory> productTypeToFactory = Map.of(
            FRUIT_BUN, new FruitBunFactory(),
            SEEDTOPPING_BUN, new SeedToppingBunFactory(),
            VEGGIE_BUN, new VeggieBunFactory(),
            PLAINGRAIN_BREAD, new PlainGrainBreadFactory(),
            SEEDTOPPING_BREAD, new SeedToppingBreadFactory(),
            WHOLEGRAIN_BREAD, new WholegrainBreadFactory()
    );

/*  public FiledBasedProductRepository() {
    productTypeToFactory.put(FRUIT_BUN, new FruitBunFactory());
    productTypeToFactory.put(PLAINGRAIN_BREAD, new PlainGrainBreadFactory());

    // ...
  }*/

    @Override
    public List<Product> findAll() {
        List<Product> products = null;
        try {
            products = Files.lines(productPath)
                    .skip(1)
                    .map(line -> {
                        String[] product = line.split(";");
                        return createProductFromProductType(product[0], Double.parseDouble(product[1]), ProductType.valueOf(product[2]));
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findByName(String name) {
        return null;
    }

    @Override
    public List<Product> findByType(ProductType productType) {
        return null;
    }

    @Override
    public void addProduct(Product productToAdd) {

    }

    @Override
    public void deleteProduct(Product productToRemove) {

    }

    public Product createProductFromProductType(String name, double price, ProductType productType) {
        return productTypeToFactory.get(productType).bake(name, price, productType);
  /*  switch (productType) {
      case FRUIT_BUN: {
        BunFactory bunFactory = new FruitBunFactory();
        return bunFactory.bakeBun(name, price, productType);
      }
      case VEGGIE_BUN: {
        BunFactory bunFactory = new VeggieBunFactory();
        return bunFactory.bakeBun(name, price, productType);
      }
      case SEEDTOPPING_BUN: {
        BunFactory bunFactory = new SeedToppingBunFactory();
        return bunFactory.bakeBun(name, price, productType);
      }
      case PLAINGRAIN_BREAD: {
        BreadFactory breadFactory = new PlainGrainBreadFactory();
        return breadFactory.bakeBread(name, price, productType);
      }
      case WHOLEGRAIN_BREAD: {
        BreadFactory breadFactory = new WholegrainBreadFactory();
        return breadFactory.bakeBread(name, price, productType);
      }
      case SEEDTOPPING_BREAD: {
        BreadFactory breadFactory = new SeedToppingBreadFactory();
        return breadFactory.bakeBread(name, price, productType);
      }
      default:
        throw new NoSuchElementException("This type of product doesn't exist in our bakery");
    }*/

    }
}
