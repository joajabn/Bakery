package pl.jablonskanycz.bakery.database.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jablonskanycz.bakery.database.domain.PersonEntity;
import pl.jablonskanycz.bakery.database.exceptions.PersonNotFoundException;
import pl.jablonskanycz.bakery.database.mapper.PersonMapper;
import pl.jablonskanycz.bakery.database.models.PersonModel;
import pl.jablonskanycz.bakery.database.repositories.PersonRepository;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;

    public List<PersonModel> getAllPeople() {
        log.info("Getting all persons");
        List<PersonModel> allPeople = personRepository.findAll().stream()
                .map(personMapper::map)
                .collect(Collectors.toList());
        log.info("Getting all persons completed.");
        return allPeople;
    }

    public PersonModel findById(long personId) {
        log.info("Getting person with ID: {}", personId);
        PersonModel personModel = personRepository.findById(personId)
                .map(personMapper::map)
                .orElseThrow(handlePersonNotFound("Person with given ID does not exist"));
        log.info("Getting person with given ID completed");
        return personModel;
    }

    @Transactional
    public long addPerson(PersonModel personModel) {
        PersonEntity saved = personRepository.save(personMapper.map(personModel));
        log.info("Adding new person completed");
        return saved.getPersonId();
    }

    @Transactional
    public PersonModel updatePerson(long personModelToUpdateId, String firstName, String lastName) {
        log.info("Updating person with ID: {}", personModelToUpdateId);
        PersonEntity personToUpdate = returnPersonIfExists(personModelToUpdateId);
        PersonEntity built = personToUpdate.toBuilder().firstName(firstName).lastName(lastName).build();
        PersonEntity updated = personRepository.save(built);
        log.info("Updating person completed");
        return personMapper.map(updated);
    }

    private PersonEntity returnPersonIfExists(Long personToUpdateId) {
        return personRepository.findById(personToUpdateId).orElseThrow(handlePersonNotFound("Person not found"));
    }

    private static Supplier<PersonNotFoundException> handlePersonNotFound(String message) {
        return () -> {
            log.warn(message);
            throw new PersonNotFoundException(message);
        };
    }

    public void deletePerson(long personToDeleteId) {
        log.info("Deleting person with ID: {}", personToDeleteId);
        personRepository.delete(returnPersonIfExists(personToDeleteId));
        log.info("Person with ID: {} was deleted successfully", personToDeleteId);
    }
}
