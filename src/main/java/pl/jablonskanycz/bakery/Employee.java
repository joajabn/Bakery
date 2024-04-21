package pl.jablonskanycz.bakery;

import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class Employee {
    private String name;
    private String surname;
    private Instant jobStartingDate;

}
