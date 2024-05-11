package pl.jablonskanycz.bakery;

import java.time.Instant;

public class Owner extends Employee {

    public Owner(String name, String surname, Instant jobStartingDate) {
        super(name, surname, jobStartingDate);
    }
}
