package pl.jablonskanycz.bakery.database.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PersonDTO {
    private Long personId;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return personId + " , " + firstName + " , " + lastName + "\n";
    }
}
