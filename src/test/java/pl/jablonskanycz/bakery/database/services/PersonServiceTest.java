package pl.jablonskanycz.bakery.database.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jablonskanycz.bakery.database.domain.PersonEntity;
import pl.jablonskanycz.bakery.database.exceptions.PersonNotFoundException;
import pl.jablonskanycz.bakery.database.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //test jednostkowy - wszystko co nie jest PersonService jest mockiem
class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    private PersonService personService;
    @Mock
    private PersonEntity personEntity;

    @Mock
    private PersonEntity personJanina;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        when(personEntity.getPersonId()).thenReturn(1L);
        when(personEntity.getFirstName()).thenReturn("Jan");
        when(personEntity.getLastName()).thenReturn("Kowalski");

        personService = new PersonService(personRepository);
    }

    @Test
    void testGetAllPeople() {
        //given
        List<PersonEntity> people = new ArrayList<>();
        people.add(personEntity);
        when(personRepository.findAll()).thenReturn(people);

        //when
        List<PersonEntity> result = personService.getAllPeople();

        //then
        assertEquals(people, result);
        verify(personRepository).findAll();
    }

    @Test
    void testAddPerson() {
        //given
        when(personJanina.getFirstName()).thenReturn("Janina");
        when(personJanina.getLastName()).thenReturn("Kowalska");

        //when
        personService.addPerson("Janina", "Kowalska");

        //then
        verify(personRepository).save(eq(PersonEntity.builder().firstName("Janina").lastName("Kowalska").build()));
//        verify(personRepository).save(eq(personJanina));
    }

    @Test
    void testUpdatePerson() {
        //given
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(personEntity));
        when(personEntity.getFirstName()).thenReturn("Janina");
        when(personEntity.getLastName()).thenReturn("Kowalska");

        //when
        personService.updatePerson(1L, "Janina", "Kowalska");

        //then
        assertEquals("Janina", personEntity.getFirstName());
        assertEquals("Kowalska", personEntity.getLastName());
        verify(personRepository).save(eq(personEntity));
        verify(personEntity).setFirstName("Janina");
        verify(personEntity).setLastName("Kowalska");
    }

    @Test
    void testUpdatePersonNotFound() {
        //given
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when then
        assertThrows(PersonNotFoundException.class, () -> personService.updatePerson(1L, "Janina", "Kowalska"));
    }

    @Test
    void testDeletePerson() {
        //given
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(personEntity));

        //when
        personService.deletePerson(1L);

        //then
        verify(personRepository).delete(eq(personEntity));
    }

    @Test
    void testDeletePersonNotFound() {
        //given
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when then
        assertThrows(PersonNotFoundException.class, () -> personService.deletePerson(1L));
    }
}