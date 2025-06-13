package pl.jablonskanycz.bakery.database.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jablonskanycz.bakery.database.mapper.AddressMapper;
import pl.jablonskanycz.bakery.database.mapper.ClientMapper;
import pl.jablonskanycz.bakery.database.mapper.PersonMapper;
import pl.jablonskanycz.bakery.database.models.AddressModel;
import pl.jablonskanycz.bakery.database.models.ClientModel;
import pl.jablonskanycz.bakery.database.models.PersonModel;
import pl.jablonskanycz.bakery.database.repositories.ClientRepository;

import java.util.List;

import static pl.jablonskanycz.bakery.database.services.PersonServiceTest.ANY_FIRSTNAME1;
import static pl.jablonskanycz.bakery.database.services.PersonServiceTest.ANY_LASTNAME1;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ClientServiceIntegrationTest {
    private final long CLIENT_ID = 1L;
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AddressMapper addressMapper;

    private final PersonModel personModel = getPersonModel();
    private final AddressModel addressModel = getAddressModel();

    private static PersonModel getPersonModel() {
        return PersonModel.builder().firstName("Adam").lastName("Nowak").build();
    }

    private static AddressModel getAddressModel() {
        return AddressModel.builder().latitude(50.45).longitude(16.73).build();
    }

    @BeforeEach
    void setUp() {
        clientService = new ClientService(clientRepository, clientMapper, personMapper, addressMapper);
    }

   // @Test
    public void shouldAddClient() {
        //given
        List<ClientModel> allClientsBefore = clientService.getAllClients();
        ClientModel clientToAdd = ClientModel.builder().person(personModel).address(addressModel).build();

        //when
        ClientModel newClient = clientService.addClient(clientToAdd);

        //then
        Assertions.assertTrue(newClient.getClientId() > 0);


    }

}

