package pl.jablonskanycz.bakery.items;

import lombok.Getter;
import lombok.ToString;
import pl.jablonskanycz.bakery.products.Product;

import java.time.Instant;
import java.util.List;
@ToString
@Getter
public class Order {
    private static int number = 1;
    private int id;
    private List<Product> orderDetails;
    private Instant orderDate;

    public Order(List<Product> orderDetails, Instant orderDate) {
        this.orderDetails = orderDetails;
        this.orderDate = orderDate;
        this.id = number;
        number++;
    }

}
