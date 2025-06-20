package pl.jablonskanycz.bakery.database.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jablonskanycz.bakery.database.dto.ClientDTO;
import pl.jablonskanycz.bakery.database.exceptions.ClientNotFoundException;
import pl.jablonskanycz.bakery.database.models.ClientModel;
import pl.jablonskanycz.bakery.database.services.ClientService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bakery/clients")
@Slf4j
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
    public ResponseEntity<ClientModel> addClient(@RequestBody ClientModel clientToAdd) {
        ClientModel newClient = clientService.addClient(clientToAdd);
//        return new ResponseEntity<>(clientId, HttpStatus.CREATED);
        return ResponseEntity.created(URI.create("/bakery/clients/" + newClient.getClientId()))
                .body(newClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientModel> updateClient(@PathVariable("id") long clientIdToUpdate, @RequestBody ClientDTO clientToUpdate) {
        ClientModel updatedClient = clientService.updateClient(clientIdToUpdate, clientToUpdate.getPerson(), clientToUpdate.getAddress());
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") long clientIdToDelete) {
        clientService.deleteClient(clientIdToDelete);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Void> handleClientNotFound(ClientNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.notFound().build();
    }
}
