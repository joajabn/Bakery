package pl.jablonskanycz.bakery.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jablonskanycz.bakery.database.domain.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository <AddressEntity, Long> {
}
