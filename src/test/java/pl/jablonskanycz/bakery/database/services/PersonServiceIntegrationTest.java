package pl.jablonskanycz.bakery.database.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jablonskanycz.bakery.database.domain.PersonEntity;
import pl.jablonskanycz.bakery.database.mapper.PersonMapper;
import pl.jablonskanycz.bakery.database.models.PersonModel;
import pl.jablonskanycz.bakery.database.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class PersonServiceIntegrationTest {
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;

    @BeforeEach
    public void setUp() {
        personService = new PersonService(personRepository, personMapper);
    }

    @Test
    public void shouldAddPerson() {
        //given
        List<PersonModel> allPeopleBefore = personService.getAllPeople();
        Optional<PersonModel> personModelBefore = allPeopleBefore.stream()
                .filter(person -> "Joanna".equals(person.getFirstName()) && "Testowa".equals(person.getLastName()))
                .findAny();
        Assertions.assertTrue(personModelBefore.isEmpty());
        PersonModel personToAdd = PersonModel.builder().firstName("Joanna").lastName("Testowa").build();

        //when
        personService.addPerson(personToAdd);

        //then
        List<PersonModel> allPeople = personService.getAllPeople();

//        #1 -> count
//        long counted = allPeople.stream()
//                .filter(person -> "Joanna".equals(person.getFirstName()) && "Testowa".equals(person.getLastName()))
//                .count();

        //#2 -> findAny
//        Optional<PersonEntity> personEntityAfter = allPeople.stream()
//                .filter(person -> "Joanna".equals(person.getFirstName()) && "Testowa".equals(person.getLastName()))
//                .findAny();
//        Assertions.assertTrue(personEntityAfter.isPresent());

        //#3 -> anyMatch
        boolean personExists = allPeople.stream()
                .anyMatch(person -> "Joanna".equals(person.getFirstName()) && "Testowa".equals(person.getLastName()));
        Assertions.assertTrue(personExists);
    }
}
