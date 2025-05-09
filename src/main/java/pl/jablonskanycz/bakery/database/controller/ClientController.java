package pl.jablonskanycz.bakery.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jablonskanycz.bakery.database.models.ClientModel;
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
}
