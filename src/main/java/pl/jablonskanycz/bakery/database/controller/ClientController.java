package pl.jablonskanycz.bakery.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jablonskanycz.bakery.database.dto.ClientDTO;
import pl.jablonskanycz.bakery.database.models.AddressModel;
import pl.jablonskanycz.bakery.database.models.ClientModel;
import pl.jablonskanycz.bakery.database.models.PersonModel;
import pl.jablonskanycz.bakery.database.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("/bakery/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @GetMapping
    public ResponseEntity<List<ClientModel>> getAllClients() {
        List<ClientModel> allClients = clientService.getAllClients();
        return new ResponseEntity<>(allClients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> getClientById(@PathVariable("id") long clientId) {
        ClientModel clientModel = clientService.findById(clientId);
        return new ResponseEntity<>(clientModel, HttpStatus.OK);
    }
    //TODO ClientDTO!
    @PostMapping
    public ResponseEntity<Long> addClient(@RequestBody ClientModel clientModel) {
        long clientId = clientService.addClient(clientModel);
        return new ResponseEntity<>(clientId, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClientModel> updateClient(@RequestBody ClientDTO clientToUpdate) {
        ClientModel updatedClient = clientService.updateClient(clientToUpdate.getClientId(), clientToUpdate.getPersonModel(),clientToUpdate.getAddressModel());
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") long clientIdToDelete) {
        clientService.deleteClient(clientIdToDelete);
        return ResponseEntity.noContent().build();
    }


}
