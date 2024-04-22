package pl.jablonskanycz.bakery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String name;
    private String surname;
    private Instant jobStartingDate;

}
