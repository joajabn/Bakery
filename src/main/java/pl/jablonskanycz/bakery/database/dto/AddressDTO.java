package pl.jablonskanycz.bakery.database.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AddressDTO {
    private long addressId;
    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return addressId + " , " + latitude + " , " + longitude + "\n";
    }
}
