package pl.jablonskanycz.bakery.database.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jablonskanycz.bakery.database.domain.AddressEntity;
import pl.jablonskanycz.bakery.database.mapper.AddressMapper;
import pl.jablonskanycz.bakery.database.models.AddressModel;
import pl.jablonskanycz.bakery.database.repositories.AddressRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    public List<AddressModel> getAllAddresses() {
        log.info("Getting all users...");
        List<AddressModel> addresses = addressRepository.findAll().stream()
                .map(addressMapper::map)
                .collect(Collectors.toList());
        log.info("Getting all users completed");
        return addresses;
    }

    public AddressModel findAddressById(long addressId){
        log.info("Getting address by ID: {}", addressId);
        AddressModel addressModel = addressRepository.findById(addressId)
                .map(addressMapper::map)
                .orElseThrow(() -> {
                    String message = "Address with given ID does not exist";
                    log.error(message);
                    throw new IllegalStateException(message);
                });
        log.info("Getting address by ID completed");
        return addressModel;
    }

    public void addAddress(AddressModel addressModel) {
        addressRepository.save(addressMapper.map(addressModel));
        log.info("Adding new address completed");
    }

    @Transactional
    public void updateAddress(AddressModel addressModelToUpdate, double latitude, double longitude) {
        log.info("Updating address with ID: {}", addressModelToUpdate.getAddressId());
        Optional<AddressEntity> addressToUpdate = returnAddressIfExists(addressMapper.map(addressModelToUpdate).getAddressId());
        if (addressToUpdate.isPresent()) {
            AddressEntity address = addressToUpdate.get();
            address.setLatitude(latitude);
            address.setLongitude(longitude);
            addressRepository.save(address);
            log.info("Updating address completed");
        } else {
            String message = "Given address does not exist";
            log.error(message);
            throw new IllegalStateException(message);
        }
    }

    private Optional<AddressEntity> returnAddressIfExists(long addressToUpdateId) {
        return addressRepository.findById(addressToUpdateId);
    }

    @Transactional
    public void deleteAddress(long addressIdToDelete) {
        log.info("Deleting address with ID: {}", addressIdToDelete);
        addressRepository.deleteById(addressIdToDelete);
        log.info("Address with ID: {} was deleted (if existed)", addressIdToDelete);
    }

//    TODO
//    @Test
//    public void shouldDeleteClientWhileDeletingAddress()
}
