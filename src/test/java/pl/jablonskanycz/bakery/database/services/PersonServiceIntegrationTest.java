package pl.jablonskanycz.bakery.database.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jablonskanycz.bakery.database.domain.PersonEntity;
import pl.jablonskanycz.bakery.database.mapper.PersonMapper;
import pl.jablonskanycz.bakery.database.models.PersonModel;
import pl.jablonskanycz.bakery.database.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static pl.jablonskanycz.bakery.database.services.PersonServiceTest.ANY_FIRSTNAME1;
import static pl.jablonskanycz.bakery.database.services.PersonServiceTest.ANY_LASTNAME1;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
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

    //@Test
    public void shouldAddPerson() {
        //given
        List<PersonModel> allPeopleBefore = personService.getAllPeople();
        long countPersonModelBefore = allPeopleBefore.stream()
                .filter(person -> ANY_FIRSTNAME1.equals(person.getFirstName()) && ANY_LASTNAME1.equals(person.getLastName()))
                .count();
        PersonModel personToAdd = PersonModel.builder().firstName(ANY_FIRSTNAME1).lastName(ANY_LASTNAME1).build();


        //when
        personService.addPerson(personToAdd);

        //then
        long countPersonModelAfter = personService.getAllPeople().stream()
                .filter(person -> ANY_FIRSTNAME1.equals(person.getFirstName()) && ANY_LASTNAME1.equals(person.getLastName()))
                .count();

        Assertions.assertEquals(countPersonModelBefore + 1, countPersonModelAfter);

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
//        boolean personExists = allPeople.stream()
//                .anyMatch(person -> ANY_FIRSTNAME1.equals(person.getFirstName()) && ANY_LASTNAME1.equals(person.getLastName()));
//        Assertions.assertTrue(personExists);
    }

    //@Test
    public void shouldUpdatePerson() {
        //given

        //when
        PersonModel personModelUpdated = personService.updatePerson(1L, "Katarzyna", "PonownieTestowa");

        //then
        Assertions.assertEquals("Katarzyna", personModelUpdated.getFirstName());
        Assertions.assertEquals("PonownieTestowa", personModelUpdated.getLastName());
        PersonModel personUpdated = personService.findById(1L);
        Assertions.assertEquals("Katarzyna", personUpdated.getFirstName());
        Assertions.assertEquals("PonownieTestowa", personUpdated.getLastName());
    }
}
