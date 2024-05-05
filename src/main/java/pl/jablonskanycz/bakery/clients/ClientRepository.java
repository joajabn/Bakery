package pl.jablonskanycz.bakery.clients;

import java.util.List;

public interface ClientRepository {
    List<Client> getAll();
    Client findBySurname(String surname);
    void addClient(Client clientToAdd);
    void updateClient(Client clientToUpdate);
    void deleteClient(Client clientToRemove);

}
