package pl.jablonskanycz.bakery.clients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.jablonskanycz.bakery.clients.address.Address;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Client {
    private int id;
    private String name;
    private String surname;
    private Address address;

}
