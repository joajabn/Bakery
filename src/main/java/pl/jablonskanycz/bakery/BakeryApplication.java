package pl.jablonskanycz.bakery;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.jablonskanycz.bakery.clients.Address;
import pl.jablonskanycz.bakery.clients.AddressRepository;
import pl.jablonskanycz.bakery.clients.Client;
import pl.jablonskanycz.bakery.clients.ClientRepository;
import pl.jablonskanycz.bakery.products.FiledBasedProductRepository;
import pl.jablonskanycz.bakery.products.ProductRepository;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class BakeryApplication {

    public static void main(String[] args) throws IOException {

//        SpringApplication.run(BakeryApplication.class, args);

//Data import
        AddressRepository addressRepository = new FileBasedAddressRepository();
        ClientRepository clientRepository = new FileBasedClientRepository();
        ProductRepository productRepository = new FiledBasedProductRepository();
        EmployeeRepository employeeRepository = new FileBasedEmployeeRepository();

        Path addressPath = Path.of("src", "main", "resources", "ADDRESS.csv");
        Path clientPath = Path.of("src", "main", "resources", "CLIENT.csv");
        Path employeePath = Path.of("src", "main", "resources", "EMPLOYEE.csv");
        Path productPath = Path.of("src", "main", "resources", "PRODUCT.csv");

        List<Address> addresses = readAddressesFrom(addressPath); // to czyta z PLIKU

        for (Address address : addresses) {
            addressRepository.addAddress(address); // a to wkłada do LISTY
        }

        List<Client> clients = readClientsFrom(clientPath, addressRepository); // pytanie
        // (adresRepo)

        for (Client client : clients) {
            clientRepository.addClient(client);
        }

        List<Employee> employees = readEmployessFrom(employeePath);

        for (Employee employee : employees) {
            employeeRepository.addEmployee(employee);
        }



    }



    private static List<Employee> readEmployessFrom(Path employeePath) throws IOException {
        List<Employee> employees = Files.lines(employeePath)
                .skip(1)
                .map(line -> {
                    String[] employee = line.split(",");
                    return new Employee(Integer.parseInt(employee[0]), employee[1], employee[2], employee[3]);
                })
                .collect(Collectors.toList());
        return employees;
    }

    private static List<Client> readClientsFrom(
        Path clientPath,
        AddressRepository addressRepository
    ) throws IOException {
      return Files.lines(clientPath)
                .skip(1)
                .map(line -> {
                    String[] client = line.split(",");
                    return new Client(
                        Integer.parseInt(client[0]),
                        client[1],
                        client[2],
                        addressRepository.findById(Integer.parseInt(client[3])));
                })
                .collect(Collectors.toList());
    }

    private static List<Address> readAddressesFrom(Path addressPath) throws IOException {
        List<Address> addresses = Files.lines(addressPath)
                .skip(1)
                .map(line -> {
                    String[] address = line.split(",");
                    return new Address(Integer.parseInt(address[0]), Double.parseDouble(address[1]), Double.parseDouble(address[2]));
                })
                .collect(Collectors.toList());
        return addresses;
    }




}





