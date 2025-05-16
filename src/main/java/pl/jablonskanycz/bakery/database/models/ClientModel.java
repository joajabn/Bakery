package pl.jablonskanycz.bakery.database.models;

import lombok.*;

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
