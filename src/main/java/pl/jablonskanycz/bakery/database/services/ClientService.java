package pl.jablonskanycz.bakery.database.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jablonskanycz.bakery.database.domain.ClientEntity;
import pl.jablonskanycz.bakery.database.exceptions.ClientNotFoundException;
import pl.jablonskanycz.bakery.database.mapper.AddressMapper;
import pl.jablonskanycz.bakery.database.mapper.ClientMapper;
import pl.jablonskanycz.bakery.database.mapper.PersonMapper;
import pl.jablonskanycz.bakery.database.models.AddressModel;
import pl.jablonskanycz.bakery.database.models.ClientModel;
import pl.jablonskanycz.bakery.database.models.PersonModel;
import pl.jablonskanycz.bakery.database.repositories.ClientRepository;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AddressMapper addressMapper;

    public List<ClientModel> getAllClients() {
        log.info("Getting all clients");
        List<ClientModel> allClients = clientRepository.findAll().stream()
                .map(clientMapper::map)
                .collect(Collectors.toList());
        log.info("Getting all clients completed");
        return  allClients;
    }

    public ClientModel findById(long clientId) {
        log.info("Getting person with ID: {}", clientId);
        ClientModel clientModel = clientRepository.findById(clientId)
                .map(clientMapper::map)
                .orElseThrow(handleClientNotFound("Client with given ID does not exist"));
        log.info("Getting client with given ID completed");
        return clientModel;
    }

    @Transactional
    public long addClient(ClientModel clientModel) {
        ClientEntity saved = clientRepository.save(clientMapper.map(clientModel));
        log.info("Adding new client completed");
        return saved.getClientId();
    }

    @Transactional
    public ClientModel updateClient(long clientToUpdateId, PersonModel personModelToUpdate, AddressModel addressModelToUpdate){
        log.info("Updating client with ID: {}", clientToUpdateId);
        ClientEntity clientToUpdate = returnClientIfExists(clientToUpdateId);
        ClientEntity built = clientToUpdate.toBuilder().person(personMapper.map(personModelToUpdate)).address(addressMapper.map(addressModelToUpdate)).build();
        ClientEntity updated = clientRepository.save(built);
        log.info("Updating client completed");
        return clientMapper.map(updated);
    }

    private static Supplier<ClientNotFoundException> handleClientNotFound(String message) {
        return () -> {
            log.warn(message);
            throw new ClientNotFoundException(message);
        };
    }

    private ClientEntity returnClientIfExists(Long clientToUpdateId) {
        return clientRepository.findById(clientToUpdateId).orElseThrow(handleClientNotFound("Client not found"));
    }

    public void deleteClient(long clientToDeleteId) {
        log.info("Deleting client with ID: {}", clientToDeleteId);
        clientRepository.delete(returnClientIfExists(clientToDeleteId));
        log.info("Client with ID: {} was deleted successfully", clientToDeleteId);
    }
}
