package pl.jablonskanycz.bakery.database.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jablonskanycz.bakery.database.domain.AddressEntity;
import pl.jablonskanycz.bakery.database.repositories.AddressRepository;

import java.util.List;

@AllArgsConstructor
@Service
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
        AddressEntity addressToUpdate = returnAddressIfExists(addressToUpdateId);
        addressToUpdate.setLatitude(latitude);
        addressToUpdate.setLongitude(longitude);
        addressRepository.save(addressToUpdate);
    }

    private AddressEntity returnAddressIfExists(Long addressToUpdateId) {
        return addressRepository.findById(addressToUpdateId).orElseThrow(() -> new IllegalArgumentException("Address with given id does not exist"));
    }

    public void deleteAddress(Long addressToDeleteId){
        AddressEntity addressToDelete = returnAddressIfExists(addressToDeleteId);
        addressRepository.delete(addressToDelete);
    }
}
