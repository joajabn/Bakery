package pl.jablonskanycz.bakery.clients;

import java.util.List;

public class FileBasedClientRepository implements ClientRepository {
    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public Client findBySurname(String surname) {
        return null;
    }

    @Override
    public void addClient(Client clientToAdd) {

    }

    @Override
    public void updateClient(Client clientToUpdate) {

    }

    @Override
    public void deleteClient(Client clientToRemove) {

    }
}
