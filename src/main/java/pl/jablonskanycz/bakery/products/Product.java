package pl.jablonskanycz.bakery.products;

import lombok.Getter;

@Getter
public abstract class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

}
