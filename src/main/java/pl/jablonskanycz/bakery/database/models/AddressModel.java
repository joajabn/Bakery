package pl.jablonskanycz.bakery.database.models;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressModel {
    private long addressId;
    private double latitude;
    private double longitude;
    @Override
    public String toString() {
        return addressId + " , " + latitude + " , " + longitude + "\n";
    }
}
