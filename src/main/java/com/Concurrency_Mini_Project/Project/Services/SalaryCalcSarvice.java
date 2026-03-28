package com.Concurrency_Mini_Project.Project.Services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;


@Service
public class SalaryCalcSarvice {

    public double calculateNewSalary(double currentSalary, String role, double projectCompletion, LocalDate joinedDate) {
        double newSalary = currentSalary;

        if (projectCompletion < 60) {
            return currentSalary;
        } else if (role.equalsIgnoreCase("Director")){
            newSalary += currentSalary * 0.05;
        }else if (role.equalsIgnoreCase("Manager")){
            newSalary += currentSalary * 0.02;
        }else if (role.equalsIgnoreCase("Employee")){
            newSalary += currentSalary * 0.01;
        }
// A new thing i learned Preiod.between() will return the full period for an employee like how many years and month and days he worked and by using .getYears it will return the years.
        int yearsWorked = Period.between(joinedDate, LocalDate.now()).getYears();
        if (yearsWorked >= 1) {
            newSalary += currentSalary * (0.02 * yearsWorked);
        }
return newSalary;
    }
}
