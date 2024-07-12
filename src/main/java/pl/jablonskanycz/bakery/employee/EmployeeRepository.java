package pl.jablonskanycz.bakery.employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();
    Employee findBySurname(String surname);
    void addEmployee(Employee employeeToAdd);
    void updateEmployee(Employee employeeWithOldData, Employee employeeWithNewData);
    void deleteEmployee(Employee employeeToRemove);
}
