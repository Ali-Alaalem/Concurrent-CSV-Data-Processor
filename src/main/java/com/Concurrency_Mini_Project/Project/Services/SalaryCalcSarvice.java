package com.Concurrency_Mini_Project.Project.Services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;


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

return newSalary;
    }
}
