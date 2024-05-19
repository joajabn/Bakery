package pl.jablonskanycz.bakery.clients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Client {
    private int id;
    private String name;
    private String surname;
    private int address_id;

}
