package pl.jablonskanycz.bakery.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jablonskanycz.bakery.database.domain.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}
