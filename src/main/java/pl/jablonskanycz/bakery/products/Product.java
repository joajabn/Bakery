package pl.jablonskanycz.bakery.products;

import lombok.Getter;

@Getter
public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void baking(){
        System.out.println("Baking!");
        System.out.println(name + " is ready!");
    }
}
