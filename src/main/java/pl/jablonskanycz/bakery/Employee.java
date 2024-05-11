package pl.jablonskanycz.bakery;


import lombok.ToString;

import java.time.Instant;

@ToString
public class Employee {
    private String name;
    private String surname;
    private Instant jobStartingDate;

    public Employee(String name, String surname, Instant jobStartingDate) {
        this.name = name;
        this.surname = surname;
        this.jobStartingDate = jobStartingDate;
    }
}
