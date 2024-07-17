package pl.jablonskanycz.bakery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.jablonskanycz.bakery.database.repositories.PersonRepository;
import pl.jablonskanycz.bakery.database.services.PersonService;

@SpringBootApplication
public class BakeryApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BakeryApplication.class, args);
        System.out.println("Repository: ");
        PersonRepository personRepository = context.getBean(PersonRepository.class);
        System.out.println(personRepository.findAll());
        System.out.println("Service: ");
        PersonService personService = context.getBean(PersonService.class);
        System.out.println(personService.getAllPeople());
    }
}