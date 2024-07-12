package pl.jablonskanycz.bakery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import pl.jablonskanycz.bakery.clients.*;
import pl.jablonskanycz.bakery.clients.address.FileBasedAddressRepository;
import pl.jablonskanycz.bakery.employee.EmployeeRepository;
import pl.jablonskanycz.bakery.employee.FileBasedEmployeeRepository;

import pl.jablonskanycz.bakery.clients.address.AddressRepository;
import pl.jablonskanycz.bakery.clients.ClientRepository;
import pl.jablonskanycz.bakery.products.FiledBasedProductRepository;
import pl.jablonskanycz.bakery.products.ProductRepository;


import java.io.IOException;

@SpringBootApplication
public class BakeryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BakeryApplication.class, args);
    }
}





