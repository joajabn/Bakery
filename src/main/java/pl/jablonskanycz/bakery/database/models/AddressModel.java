package pl.jablonskanycz.bakery.database.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressModel {
    private long addressId;
    private double latitude;
    private double longitude;
}
