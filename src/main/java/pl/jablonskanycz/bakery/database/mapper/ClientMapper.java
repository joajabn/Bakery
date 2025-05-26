package pl.jablonskanycz.bakery.database.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jablonskanycz.bakery.database.domain.ClientEntity;
import pl.jablonskanycz.bakery.database.models.ClientModel;


@Component
public class ClientMapper {
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AddressMapper addressMapper;
    public ClientModel map(ClientEntity clientEntity){
        if (clientEntity == null){
            return null;
        }
        ClientModel clientModel = new ClientModel();
        return clientModel.builder()
                .clientId(clientEntity.getClientId())
                .person(personMapper.map(clientEntity.getPerson()))
                .address(addressMapper.map(clientEntity.getAddress()))
                .build();
    }

    public ClientEntity map(ClientModel clientModel){
        if (clientModel == null){
            return null;
        }
        ClientEntity clientEntity = new ClientEntity();
        return clientEntity.builder()
                .clientId(clientModel.getClientId())
                .person(personMapper.map(clientModel.getPerson()))
                .address(addressMapper.map(clientModel.getAddress()))
                .build();
    }
}
