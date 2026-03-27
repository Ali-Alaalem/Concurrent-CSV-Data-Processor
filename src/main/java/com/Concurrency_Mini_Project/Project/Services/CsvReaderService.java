package com.Concurrency_Mini_Project.Project.Services;


import com.Concurrency_Mini_Project.Project.Exceptions.InvalidCSVException;
import com.Concurrency_Mini_Project.Project.Models.Employee;
import org.springframework.stereotype.Service;



import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CsvReaderService {

    public List<Employee> readCSV() {
        List<Employee> employees = new ArrayList<>();
        File file = new File("Employees.csv");

        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] user = line.split(",");

                Employee employee = new Employee();

                employee.setName(user[0]);
                employee.setSalary(Double.parseDouble(user[1]));
                employee.setJoinedDate(LocalDate.parse(user[2]));
                employee.setRole(user[3]);
                employee.setProjectCompletionPercentage(Double.parseDouble(user[4]));

                employees.add(employee);
            }

        } catch (Exception e) {
            throw new InvalidCSVException("The Csv file is not exist or have bad data");
        }

        return employees;
    }
}
