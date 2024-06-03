package pl.jablonskanycz.bakery.clients;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
public class ListBasedAddressRepository implements AddressRepository {
    private List<Address> addresses = new ArrayList<>();
    @Override
    public final List<Address> getAll() {
        return addresses;
    }

    @Override
    public void addAddress(Address addressToAdd) {
        addresses.add(addressToAdd);
    }

    @Override
    public Address findById(int id) {
        return addresses.stream()
                .filter(address -> id == address.getId())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("There's no clients with given address_id in our bakery."));
    }
}
