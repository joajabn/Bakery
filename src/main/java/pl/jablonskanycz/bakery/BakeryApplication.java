package pl.jablonskanycz.bakery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.List;

@SpringBootApplication
public class BakeryApplication {

    public static void main(String[] args) {

//        SpringApplication.run(BakeryApplication.class, args);
        Order order1 = new Order(null, null);
        Order order2 = new Order(null, null);
        System.out.println(order1.getId());
        System.out.println(order2.getId());


    }

}
