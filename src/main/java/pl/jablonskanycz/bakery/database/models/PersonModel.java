package pl.jablonskanycz.bakery.database.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonModel {
    private Long personId;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return personId + " , " + firstName + " , " + lastName + "\n";
    }
}
