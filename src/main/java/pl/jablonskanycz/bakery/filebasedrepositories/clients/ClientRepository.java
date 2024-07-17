package pl.jablonskanycz.bakery.filebasedrepositories.clients;

import java.util.List;

public interface ClientRepository {
    List<Client> getAll();
    Client findBySurname(String surname);
    void addClient(Client clientToAdd);
    void updateClient(Client clientWithOldData, Client clientWithNewData);
    void deleteClient(Client clientToRemove);

}
