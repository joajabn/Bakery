package pl.jablonskanycz.bakery;

import java.util.List;

public interface ClientRepository {
    List<Client> getAll();
    Client findBySurname(String surname);
    void addClient(Client clientToAdd);
    void deleteClient(Client clientToRemove);

}
