package pl.jablonskanycz.bakery.database.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jablonskanycz.bakery.database.domain.PersonEntity;
import pl.jablonskanycz.bakery.database.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class PersonServiceTest {
    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    private PersonEntity personEntity;

    @BeforeEach
    void setup() {
        personEntity = PersonEntity.builder()
                .personId(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .build();
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
        //when
        personService.addPerson("Janina", "Kowalska");

        //then
        verify(personRepository).save(any(PersonEntity.class));
    }

    @Test
    void testUpdatePerson() {
        //given
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(personEntity));

        //when
        personService.updatePerson(1L, "Janina", "Kowalska");

        //then
        assertEquals("Janina", personEntity.getFirstName());
        assertEquals("Kowalska", personEntity.getLastName());
        verify(personRepository).save(any(PersonEntity.class));
    }

    @Test
    void testUpdatePersonNotFound() {
        //given
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when then
        assertThrows(RuntimeException.class, () -> personService.updatePerson(1L, "Janina", "Kowalska"));
    }

    @Test
    void testDeletePerson() {
        //given
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(personEntity));

        //when
        personService.deletePerson(1L);

        //then
        verify(personRepository).delete(any(PersonEntity.class));
    }

    @Test
    void testDeletePersonNotFound() {
        //given
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when then
        assertThrows(RuntimeException.class, () -> personService.deletePerson(1L));
    }
}