package com.Concurrency_Mini_Project.Project.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

        private String name;
        private double salary;
        private LocalDate joinedDate;
        private String role;
        private double projectCompletionPercentage;

}
