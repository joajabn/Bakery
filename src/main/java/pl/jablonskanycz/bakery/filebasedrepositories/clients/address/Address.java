package pl.jablonskanycz.bakery.filebasedrepositories.clients.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class Address {
    private int id;
    private double latitude;
    private double longitude;

}