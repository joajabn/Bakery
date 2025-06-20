package pl.jablonskanycz.bakery.database.dto;

import lombok.*;
import pl.jablonskanycz.bakery.database.models.AddressModel;
import pl.jablonskanycz.bakery.database.models.PersonModel;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class ClientDTO {

    private Long clientId;

    @NonNull
    private PersonModel person;

    @NonNull
    private AddressModel address;

    @Override
    public String toString() {
        return clientId + " , " + person + " , " + address;
    }
}
