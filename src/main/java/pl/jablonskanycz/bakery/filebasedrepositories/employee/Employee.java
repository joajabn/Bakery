package pl.jablonskanycz.bakery.filebasedrepositories.employee;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
@Getter
@Setter
@ToString
@Builder
public class Employee {
    private int id;
    private String name;
    private String surname;
    private String jobStartingDate;

    public Employee(int id, String name, String surname, String jobStartingDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jobStartingDate = jobStartingDate;
    }
}
