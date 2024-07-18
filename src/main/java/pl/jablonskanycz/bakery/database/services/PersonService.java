package pl.jablonskanycz.bakery.database.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jablonskanycz.bakery.database.domain.PersonEntity;
import pl.jablonskanycz.bakery.database.repositories.PersonRepository;
import java.util.List;

@AllArgsConstructor
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonEntity> getAllPeople() {
        return personRepository.findAll();
    }

    public void addPerson(String firstName, String lastName) {
        personRepository.save(PersonEntity.builder().firstName(firstName).lastName(lastName).build());
    }

    @Transactional
    public void updatePerson(Long personToUpdateId, String firstName, String lastName) {
        PersonEntity personToUpdate = returnPersonIfExists(personToUpdateId);
        personToUpdate.setFirstName(firstName);
        personToUpdate.setLastName(lastName);
        personRepository.save(personToUpdate);
    }

    private PersonEntity returnPersonIfExists(Long personToUpdateId) {
        return personRepository.findById(personToUpdateId).orElseThrow(() -> new RuntimeException("Person not found"));
    }

    public void deletePerson(Long personToDeleteId){
        personRepository.delete(returnPersonIfExists(personToDeleteId));
    }
}
