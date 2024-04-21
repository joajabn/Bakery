package pl.jablonskanycz.bakery;

import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class Order {
    private int number;
    private List<Bread> orderDetails;
    private Instant orderDate;

    public Order(List<Bread> orderDetails, Instant orderDate) {
        this.orderDetails = orderDetails;
        this.orderDate = orderDate;
        this.number = getNumber();
        number++;

    }

    @Override
    public String toString() {
        return "Order{" +
                "orderDetails=" + orderDetails +
                ", orderDate=" + orderDate +
                ", number=" + number +
                '}';
    }
}
