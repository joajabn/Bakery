package pl.jablonskanycz.bakery.database.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientModel {
    private Long clientId;
    private PersonModel person;
    private AddressModel address;

    @Override
    public String toString() {
        return clientId + " , " + person + " , " + address;
    }
}
