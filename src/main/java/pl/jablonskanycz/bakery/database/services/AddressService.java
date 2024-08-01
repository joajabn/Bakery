package pl.jablonskanycz.bakery.database.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jablonskanycz.bakery.database.domain.AddressEntity;
import pl.jablonskanycz.bakery.database.repositories.AddressRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<AddressEntity> getAllAddresses() {
        return addressRepository.findAll();
    }

    public void addAddress(Double latitude, Double longitude) {
        addressRepository.save(AddressEntity.builder().latitude(latitude).longitude(longitude).build());
    }

    @Transactional
    public void updateAddress(Long addressToUpdateId, Double latitude, Double longitude) {
        Optional<AddressEntity> addressToUpdate = returnAddressIfExists(addressToUpdateId);
        if (addressToUpdate.isPresent()) {
            addressToUpdate.get().setLatitude(latitude);
            addressToUpdate.get().setLongitude(longitude);
            addressRepository.save(addressToUpdate.get());
        } else {
            throw new IllegalStateException("Address with given id does not exist");
        }
    }

    private Optional<AddressEntity> returnAddressIfExists(Long addressToUpdateId) {
        return addressRepository.findById(addressToUpdateId);
    }

    @Transactional
    public void deleteAddress(Long addressToDeleteId) {
        Optional<AddressEntity> addressToDelete = returnAddressIfExists(addressToDeleteId);
        if(addressToDelete.isPresent()){
            log.info("Deleting address with ID: {}", addressToDeleteId);
            addressRepository.delete(addressToDelete.get());
            log.info("Address with ID: {} successfully deleted", addressToDeleteId);
        } else {
            log.warn("Address with ID: {} not found", addressToDeleteId);
        }
    }
}
