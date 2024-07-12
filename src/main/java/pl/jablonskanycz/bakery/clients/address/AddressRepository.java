package pl.jablonskanycz.bakery.clients.address;

import java.util.List;

public interface AddressRepository {
    List<Address> getAll();
    void addAddress(Address addressToAdd);
    Address findById(int id);
}
