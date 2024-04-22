package pl.jablonskanycz.bakery;

import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.List;
@ToString
@Getter
public class Order {
    private static int number = 1;
    private int id;
    private List<Bread> orderDetails;
    private Instant orderDate;

    public Order(List<Bread> orderDetails, Instant orderDate) {
        this.orderDetails = orderDetails;
        this.orderDate = orderDate;
        this.id = number;
        number++;

    }

}
