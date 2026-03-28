package com.Concurrency_Mini_Project.Project.Services;

import com.Concurrency_Mini_Project.Project.Models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Service
public class ConcurrentProcessingService {

    private final SalaryCalcSarvice salaryCalcSarvice;

    public ConcurrentProcessingService(SalaryCalcSarvice salaryCalcSarvice) {
        this.salaryCalcSarvice = salaryCalcSarvice;
    }

    public void processSalaries(List<Employee> employees) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Semaphore semaphore = new Semaphore(2);

    }
}