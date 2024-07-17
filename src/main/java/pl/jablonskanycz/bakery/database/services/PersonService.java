package pl.jablonskanycz.bakery.database.services;

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

    public List<PersonEntity> getAllPeople(){
        return personRepository.findAll();
    }
}
