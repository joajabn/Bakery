package pl.jablonskanycz.bakery;

import pl.jablonskanycz.bakery.clients.Client;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();
    Employee findBySurname(String surname);
    void addEmployee(Employee employeeToAdd);
    void updateEmployee(Employee employeeToUpdate);
    void deleteEmployee(Employee employeeToRemove);
}
