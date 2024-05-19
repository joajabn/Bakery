package pl.jablonskanycz.bakery;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.jablonskanycz.bakery.clients.Address;
import pl.jablonskanycz.bakery.clients.Client;
import pl.jablonskanycz.bakery.products.Product;
import pl.jablonskanycz.bakery.products.ProductType;
import pl.jablonskanycz.bakery.products.bread.BreadFactory;
import pl.jablonskanycz.bakery.products.bread.PlainGrainBreadFactory;
import pl.jablonskanycz.bakery.products.bread.SeedToppingBreadFactory;
import pl.jablonskanycz.bakery.products.bread.WholegrainBreadFactory;
import pl.jablonskanycz.bakery.products.bun.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@SpringBootApplication
public class BakeryApplication {

    public static void main(String[] args) throws IOException {

//        SpringApplication.run(BakeryApplication.class, args);

//Data import

        Path clientPath = Path.of("src", "main", "resources", "CLIENT.csv");
        Path addressPath = Path.of("src", "main", "resources", "ADDRESS.csv");
        Path employeePath = Path.of("src", "main", "resources", "EMPLOYEE.csv");
        Path productPath = Path.of("src", "main", "resources", "PRODUCT.csv");

        List<Client> clients = Files.lines(clientPath)
                .skip(1)
                .map(line -> {
                    String[] client = line.split(",");
                    return new Client(Integer.parseInt(client[0]), client[1], client[2], Integer.parseInt(client[3]));
                })
                .collect(Collectors.toList());

        List<Address> addresses = Files.lines(addressPath)
                .skip(1)
                .map(line -> {
                    String[] address = line.split(",");
                    return new Address(Integer.parseInt(address[0]), Double.parseDouble(address[1]), Double.parseDouble(address[2]));
                })
                .collect(Collectors.toList());

        List<Employee> employees = Files.lines(employeePath)
                .skip(1)
                .map(line -> {
                    String[] employee = line.split(",");
                    return new Employee(Integer.parseInt(employee[0]), employee[1], employee[2], employee[3]);
                })
                .collect(Collectors.toList());

        List<Product> products = Files.lines(productPath)
                .skip(1)
                .map(line -> {
                    String[] product = line.split(";");
                    return createProductFromProductType(product[0], Double.parseDouble(product[1]), ProductType.valueOf(product[2]));
                })
                .collect(Collectors.toList());

    }

    public static Product createProductFromProductType(String name, double price, ProductType productType) {
        switch (productType) {
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
        }

    }


}





