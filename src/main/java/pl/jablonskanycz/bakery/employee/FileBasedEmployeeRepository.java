package pl.jablonskanycz.bakery.employee;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.APPEND;

public class FileBasedEmployeeRepository implements EmployeeRepository {
    private static final Path EMPLOYEE_PATH = Path.of("src", "main", "resources", "EMPLOYEE.csv");

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            employees = Files.lines(EMPLOYEE_PATH)
                    .skip(1)
                    .map(FileBasedEmployeeRepository::getEmployee)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;

    }

    @Override
    public Employee findBySurname(String surname) {
        return getAll().stream()
                .filter(e -> surname.equals(e.getSurname()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Client list is empty"));
    }

    private static Employee getEmployee(String line) {
        String[] employee = line.split(",");
        return new Employee(
                Integer.parseInt(employee[0]),
                employee[1],
                employee[2],
                employee[3]);
    }

    @Override
    public void addEmployee(Employee employeeToAdd) {
        List<String> employeeList = new ArrayList<>();
        try {
            employeeList = Files.lines(EMPLOYEE_PATH)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = convertEmployeeToString(employeeToAdd, employeeList);
        try {
            Files.writeString(EMPLOYEE_PATH, line, APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String convertEmployeeToString(Employee employeeToAdd, List<String> employeeList) {
        String lastId = employeeList.getLast().split(",")[0];
        int newId = Integer.parseInt(lastId) + 1;
        return "\n" + newId + "," + employeeToAdd.getName() + "," + employeeToAdd.getSurname() + "," + employeeToAdd.getJobStartingDate();
    }

    @Override
    public void updateEmployee(Employee employeeWithOldData, Employee employeeWithNewData) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_PATH.toString()));
            List<String> newFileContent = reader.lines()
                    .map(line -> {
                        if (line.startsWith(String.valueOf(employeeWithOldData.getId()))) {
                            return employeeWithNewData.getId() + "," + employeeWithNewData.getName() + "," + employeeWithNewData.getSurname() + "," + employeeWithNewData.getJobStartingDate();
                        } else {
                            return line;
                        }
                    })
                    .collect(Collectors.toList());
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_PATH.toString()));
            for (int i = 0; i < newFileContent.size(); i++) {
                if (i == 0) {
                    writer.write(newFileContent.get(i));
                    writer.newLine();
                } else {
                    writer.append(newFileContent.get(i));
                    writer.newLine();
                }
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteEmployee(Employee employeeToRemove) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_PATH.toString()));
            List<String> newFileContent = reader.lines().collect(Collectors.toList());
            for (int i = 0; i < newFileContent.size(); i++) {
                if (newFileContent.get(i).startsWith(String.valueOf(employeeToRemove.getId()))) {
                    newFileContent.remove(newFileContent.get(i));
                }
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_PATH.toString()));
            for (int i = 0; i < newFileContent.size(); i++) {
                if (i == 0) {
                    writer.write(newFileContent.get(i));
                    writer.newLine();
                } else {
                    writer.append(newFileContent.get(i));
                    writer.newLine();
                }
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/* stream<string> to object
                String[] strings = line.split(",");
                return new Employee(
                        Integer.parseInt(strings[0]),
                        strings[1],
                        strings[2],
                        strings[3]);
            })
*/

/* update employee with stream
        try {
            Optional<Employee> client = Files.lines(employeePath)
                    .skip(1)
                    .filter(line -> {
                        String[] strings = line.split(",");
                        return Integer.parseInt(strings[0]) == employeeWithNewData.getId();
                    })
                    .map(e -> new Employee(
                            employeeWithNewData.getId(),
                            employeeWithNewData.getName(),
                            employeeWithNewData.getSurname(),
                            employeeWithNewData.getJobStartingDate()))
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
