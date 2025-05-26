package pl.jablonskanycz.bakery.database.dto;

import lombok.*;
import pl.jablonskanycz.bakery.database.models.AddressModel;
import pl.jablonskanycz.bakery.database.models.PersonModel;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ClientDTO {
    private Long clientId;
    private PersonModel personModel;
    private AddressModel addressModel;

    @Override
    public String toString() {
        return clientId + " , " + personModel + " , " + addressModel;
    }
}
