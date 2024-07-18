package pl.jablonskanycz.bakery.filebasedrepositories.items;

import pl.jablonskanycz.bakery.filebasedrepositories.employee.Employee;

public class Owner extends Employee {

    public Owner(int id, String name, String surname, String jobStartingDate) {
        super(id, name, surname, jobStartingDate);
    }
}
