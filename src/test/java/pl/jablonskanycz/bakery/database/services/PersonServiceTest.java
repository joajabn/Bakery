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
import pl.jablonskanycz.bakery.database.domain.AddressEntity;
import pl.jablonskanycz.bakery.database.domain.PersonEntity;
import pl.jablonskanycz.bakery.database.exceptions.PersonNotFoundException;
import pl.jablonskanycz.bakery.database.mapper.PersonMapper;
import pl.jablonskanycz.bakery.database.models.AddressModel;
import pl.jablonskanycz.bakery.database.models.PersonModel;
import pl.jablonskanycz.bakery.database.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//test jednostkowy - wszystko co nie jest PersonService jest mockiem
class PersonServiceTest {
    public static final long PERSON_ENTITY_ID1 = 1L;
    public static final String ANY_FIRSTNAME1 = "Jan";
    public static final String ANY_LASTNAME1 = "Kowalski";
    private PersonService personService;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper personMapper;
    @Mock
    private PersonModel personModel;
    @Mock
    private PersonEntity personEntity;
    @Mock
    private PersonEntity personJanina;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        when(personEntity.getPersonId()).thenReturn(PERSON_ENTITY_ID1);
        when(personEntity.getFirstName()).thenReturn(ANY_FIRSTNAME1);
        when(personEntity.getLastName()).thenReturn(ANY_LASTNAME1);

        when(personModel.getPersonId()).thenReturn(PERSON_ENTITY_ID1);
        when(personModel.getFirstName()).thenReturn(ANY_FIRSTNAME1);
        when(personModel.getLastName()).thenReturn(ANY_LASTNAME1);

        when(personMapper.map(personEntity)).thenReturn(personModel);
        when(personMapper.map(personModel)).thenReturn(personEntity);

        personService = new PersonService(personRepository, personMapper);
    }

    @Test
    void shouldGetAllPeople() {
        //given
        List<PersonEntity> people = new ArrayList<>();
        people.add(personEntity);
        when(personRepository.findAll()).thenReturn(people);

        //when
        List<PersonModel> result = personService.getAllPeople();

        //then
        List<PersonModel> expected = List.of(personModel);
        assertEquals(expected, result);
        verify(personRepository).findAll();
    }

    @Test
    void shouldAddPerson() {
        //given
        when(personRepository.save(personEntity)).thenReturn(personEntity);
        //when
        long actual = personService.addPerson(personModel);

        //then
        assertEquals(actual, PERSON_ENTITY_ID1);
        verify(personRepository).save(eq(personEntity));
    }
    @Test
    void shouldFindById() {
        //given
        long personId = 1L;
        when(personRepository.findById(personId)).thenReturn(Optional.of(personEntity));

        //when
        PersonModel personFound = personService.findById(personId);

        //then
        assertNotNull(personFound);
        assertEquals(personModel.getPersonId(), personEntity.getPersonId());
        assertEquals(personModel.getFirstName(), personEntity.getFirstName());
        assertEquals(personModel.getLastName(), personEntity.getLastName());
    }


    @Test
    void shouldUpdatePersonNotFound() {
        //given
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when then
        assertThrows(PersonNotFoundException.class, () -> personService.updatePerson(1L, "Janina", "Kowalska"));
    }

    @Test
    void shouldDeletePerson() {
        //given
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(personEntity));

        //when
        personService.deletePerson(1L);

        //then
        verify(personRepository).delete(eq(personEntity));
    }

    @Test
    void shouldDeletePersonNotFound() {
        //given
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when then
        assertThrows(PersonNotFoundException.class, () -> personService.deletePerson(1L));
    }
}