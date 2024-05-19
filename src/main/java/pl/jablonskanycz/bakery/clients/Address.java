package pl.jablonskanycz.bakery.clients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Address {
    private int id;
    private double latitude;
    private double longitude;

}