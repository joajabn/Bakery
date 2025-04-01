package pl.jablonskanycz.bakery.database.mapper;

import org.springframework.stereotype.Component;
import pl.jablonskanycz.bakery.database.domain.PersonEntity;
import pl.jablonskanycz.bakery.database.models.PersonModel;

@Component
public class PersonMapper {

    public PersonModel map(PersonEntity personEntity){
        if (personEntity == null){
            return null;
        }
        PersonModel personModel = new PersonModel();
        return personModel.builder().personId(personEntity.getPersonId()).firstName(personEntity.getFirstName()).lastName(personEntity.getLastName()).build();
    }

    public PersonEntity map(PersonModel personModel){
        if (personModel == null){
            return null;
        }
        PersonEntity personEntity = new PersonEntity();
        return personEntity.builder().personId(personModel.getPersonId()).firstName(personModel.getFirstName()).lastName(personModel.getLastName()).build();
    }
}
