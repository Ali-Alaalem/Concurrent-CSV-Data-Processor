package com.Concurrency_Mini_Project.Project.Controllers;

import com.Concurrency_Mini_Project.Project.Models.Employee;
import com.Concurrency_Mini_Project.Project.Services.ConcurrentProcessingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final ConcurrentProcessingService concurrentProcessingService;

    public EmployeeController(ConcurrentProcessingService concurrentProcessingService) {
        this.concurrentProcessingService = concurrentProcessingService;
    }

    @GetMapping("/process-salaries")
    public List<Employee> processSalaries() {
        return concurrentProcessingService.processSalaries();
    }

}
