package pl.jablonskanycz.bakery.clients.address;

import pl.jablonskanycz.bakery.clients.address.Address;
import pl.jablonskanycz.bakery.clients.address.AddressRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.APPEND;

public class FileBasedAddressRepository implements AddressRepository {
    private static final Path addressPath = Path.of("src", "main", "resources", "ADDRESS.csv");

    @Override
    public List<Address> getAll() {
        List<Address> addresses = null;
        try {
            addresses = Files.lines(addressPath)
                    .skip(1)
                    .map(line -> {
                        String[] address = line.split(",");
                        return new Address(Integer.parseInt(address[0]), Double.parseDouble(address[1]), Double.parseDouble(address[2]));
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    @Override
    public void addAddress(Address addressToAdd) {
        List<String> addressesList = null;
        try {
            addressesList = Files.lines(addressPath)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String lastId = addressesList.get(addressesList.size() - 1).split(",")[0];
        int newId = Integer.parseInt(lastId) + 1;
        String line = "\n" + newId + "," + addressToAdd.getLatitude() + "," + addressToAdd.getLongitude();
        try {
            Files.writeString(addressPath, line, APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address findById(int id) {
        Address address = null;
        try {
            return Files.lines(addressPath)
                    .skip(1)
                    .map(line -> {
                        String[] strings = line.split(",");
                        return new Address(Integer.parseInt(strings[0]), Double.parseDouble(strings[1]), Double.parseDouble(strings[2]));
                    })
                    .filter(a -> id == a.getId())
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("There's no clients with given address_id in our bakery."));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }


}
