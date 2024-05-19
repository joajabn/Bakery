package pl.jablonskanycz.bakery.products;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Product {
    private String name;
    private double price;
    private ProductType productType;

    public Product(String name, double price, ProductType productType) {
        this.name = name;
        this.price = price;
        this.productType = productType;
    }

}
