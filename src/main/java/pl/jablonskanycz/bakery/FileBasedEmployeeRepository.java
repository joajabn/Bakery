package pl.jablonskanycz.bakery;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;

public class FileBasedEmployeeRepository implements EmployeeRepository {
    private static final Path employeePath = Path.of("src", "main", "resources", "EMPLOYEE.csv");

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = null;
        try {
            employees = Files.lines(employeePath)
                    .skip(1)
                    .map(line -> {
                        String[] employee = line.split(",");
                        return new Employee(
                                Integer.parseInt(employee[0]),
                                employee[1],
                                employee[2],
                                employee[3]);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;

    }

    @Override
    public Employee findBySurname(String surname) {
        Employee employee = null;
        try {
            return Files.lines(employeePath)
                    .skip(1)
                    .map(line -> {
                        String[] strings = line.split(",");
                        return new Employee(
                                Integer.parseInt(strings[0]),
                                strings[1],
                                strings[2],
                                strings[3]);
                    })
                    .filter(e -> surname.equals(e.getSurname()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Client list is empty"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employee;

    }

    @Override
    public void addEmployee(Employee employeeToAdd) {
        List<String> employeeList = null;
        try {
            employeeList = Files.lines(employeePath)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String lastId = employeeList.get(employeeList.size() - 1).split(",")[0];
        int newId = Integer.parseInt(lastId) + 1;
        String line = "\n" + newId + "," + employeeToAdd.getName() + "," + employeeToAdd.getSurname() + "," + employeeToAdd.getJobStartingDate();
        try {
            Files.writeString(employeePath, line, APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateEmployee(Employee employeeWithOldData, Employee employeeWithNewData) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(employeePath.toString()));
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(employeePath.toString()));
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
            BufferedReader reader = new BufferedReader(new FileReader(employeePath.toString()));
            List<String> newFileContent = reader.lines().collect(Collectors.toList());
            for (int i = 0; i < newFileContent.size(); i++) {
                if (newFileContent.get(i).startsWith(String.valueOf(employeeToRemove.getId()))) {
                    newFileContent.remove(newFileContent.get(i));
                }
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(employeePath.toString()));
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
