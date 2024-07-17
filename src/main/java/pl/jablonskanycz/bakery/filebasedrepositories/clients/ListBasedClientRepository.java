package pl.jablonskanycz.bakery.filebasedrepositories.clients;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ListBasedClientRepository implements ClientRepository {
    private final List<Client> clients = new ArrayList<>();
    @Override
    public List<Client> getAll() {
        return clients;
    }

    @Override
    public Client findBySurname(String surname) {
        return clients.stream()
                .filter(client -> surname.equals(client.getSurname()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("There's no clients with given surname in our bakery."));
    }

    @Override
    public void addClient(Client clientToAdd) {
        clients.add(clientToAdd);
    }

    @Override
    public void updateClient(Client clientWithOldData, Client clientWithNewData) {

    }

    @Override
    public void deleteClient(Client clientToRemove) {
        if(!clients.isEmpty()){
            clients.remove(clientToRemove);
        } else {
            throw new NoSuchElementException("Client list is empty");
        }

    }
}
