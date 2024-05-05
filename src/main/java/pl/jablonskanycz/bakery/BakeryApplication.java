package pl.jablonskanycz.bakery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.jablonskanycz.bakery.clients.Client;
import pl.jablonskanycz.bakery.products.*;

import java.time.Instant;
import java.util.List;

@SpringBootApplication
public class BakeryApplication {

    public static void main(String[] args) {

//        SpringApplication.run(BakeryApplication.class, args);
//        Bun rhubarbBun = new FruitBun("rabarbar", new Bun("drożdzówka z rabarbarem", 9.0));
//        rhubarbBun.baking();
//        Bun asparagusBun = new VeggieBun("szparagi", new Bun("drożdżówka ze szparagami", 10.0));
//        asparagusBun.baking();
//        Bun sesameBun = new SeedToppingBun("sezam", new Bun("sezam", 5.6));
//        sesameBun.baking();
//        Bread linseedBread = new SeedToppingBread("siemie lnianie", new Bread("chleb z siemieniem lnianym", 8.5));
//        Bread ryeWholegrainBread = new WholegrainBread("żyto", new Bread("chleb żytni pełnoziarnisty", 9.5));
//
//        Order order1 = new Order(List.of(rhubarbBun, sesameBun, ryeWholegrainBread), null);
//        Order order2 = new Order(List.of(asparagusBun, linseedBread), null);
//        System.out.println(order1);
//        System.out.println(order2.getId());

        Client client1 = new Client("Adam", "Kowalski", 51.20, 15.80);
        Client client2 = new Client("Karol", "Nowak", 50.02, 18.30);
        System.out.println(client1);
        System.out.println(client2);
        Employee employee1 = new Employee("Karolina", "Rak", Instant.now());
        Employee employee2 = new Employee("Weronika", "Kot", Instant.now());
        System.out.println(employee1);
        System.out.println(employee2);
//        Owner owner = new Owner("Piotr", "Tor", Instant.now());






    }

}
