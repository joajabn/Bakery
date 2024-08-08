package pl.jablonskanycz.bakery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.jablonskanycz.bakery.database.models.AddressModel;
import pl.jablonskanycz.bakery.database.services.AddressService;

@SpringBootApplication
public class BakeryApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BakeryApplication.class, args);
//        System.out.println("Person repository:");
//        PersonRepository personRepository = context.getBean(PersonRepository.class);
//        System.out.println(personRepository.findAll());
//        System.out.println("Person service:");
//        PersonService personService = context.getBean(PersonService.class);
//        System.out.println(personService.getAllPeople());
//        personService.addPerson("JOANNA", "NYCZ");
//        System.out.println(personService.getAllPeople());
//        System.out.println("update person:");
//        personService.updatePerson(2L, "Karol", "Nowakowski");
//        System.out.println(personService.getAllPeople());
//        System.out.println("delete person:");
//        personService.deletePerson(12L);
//        System.out.println(personService.getAllPeople());
        System.out.println("Address service:");
        AddressService addressService = context.getBean(AddressService.class);
        System.out.println(addressService.getAllAddresses());
        AddressModel addressModel = AddressModel.builder().latitude(16.57).longitude(17.89).build();
        addressService.addAddress(addressModel);
        System.out.println(addressService.getAllAddresses());
        System.out.println(addressService.findAddressById(5L));
//        addressService.updateAddress(1L, 16.90, 18.30);
//        System.out.println(addressService.getAllAddresses());
//        addressService.deleteAddress(4L);
//        System.out.println(addressService.getAllAddresses());

    }
}