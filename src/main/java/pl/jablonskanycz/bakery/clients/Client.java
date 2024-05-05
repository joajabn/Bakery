package pl.jablonskanycz.bakery.clients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Client {
    private String name;
    private String surname;
    private double latitude;
    private double longitude;
}
