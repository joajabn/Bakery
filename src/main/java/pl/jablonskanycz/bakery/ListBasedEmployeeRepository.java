package pl.jablonskanycz.bakery;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ListBasedEmployeeRepository implements EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();
    @Override
    public List<Employee> getAll() {
        return employees;
    }

    @Override
    public Employee findBySurname(String surname) {
        return employees.stream()
                .filter(employee -> surname.equals(employee.getSurname()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("There's no employee with given surname in our bakery."));
    }

    @Override
    public void addEmployee(Employee employeeToAdd) {
        employees.add(employeeToAdd);
    }

    @Override
    public void updateEmployee(Employee employeeWithOldData, Employee employeeWithNewData) {

    }

    @Override
    public void deleteEmployee(Employee employeeToRemove) {
        if(!employees.isEmpty()){
            employees.remove(employeeToRemove);
        } else {
            throw new NoSuchElementException("Employee list is empty");
        }
    }
}
