package pl.jablonskanycz.bakery.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jablonskanycz.bakery.database.domain.ClientEntity;
@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
