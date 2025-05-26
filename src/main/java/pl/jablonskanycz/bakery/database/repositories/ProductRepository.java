package pl.jablonskanycz.bakery.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jablonskanycz.bakery.database.domain.ProductEntity;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
