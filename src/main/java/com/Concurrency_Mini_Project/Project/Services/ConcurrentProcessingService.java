package com.Concurrency_Mini_Project.Project.Services;

import com.Concurrency_Mini_Project.Project.Models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Service
public class ConcurrentProcessingService {
    private final CsvReaderService csvReaderService;
    private final SalaryCalcSarvice salaryCalcSarvice;

    public ConcurrentProcessingService(CsvReaderService csvReaderService, SalaryCalcSarvice salaryCalcSarvice) {
        this.csvReaderService = csvReaderService;
        this.salaryCalcSarvice = salaryCalcSarvice;
    }

    public List<Employee> processSalaries() {

        List<Employee> employees = csvReaderService.readCSV();

        ExecutorService executor = Executors.newFixedThreadPool(4);
        Semaphore semaphore = new Semaphore(2);

        for (Employee emp : employees) {
            executor.submit(() -> {
                try {
                    semaphore.acquire();
                    double newSalary = salaryCalcSarvice.calculateNewSalary(
                            emp.getSalary(),
                            emp.getRole(),
                            emp.getProjectCompletionPercentage(),
                            emp.getJoinedDate()
                    );
                    synchronized (emp) {
                        System.out.println("Employee: " + emp.getName() +
                                " | Old Salary: " + emp.getSalary() +
                                " | New Salary: " + newSalary);
                        emp.setSalary(newSalary);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        executor.shutdown();
        return employees;
    }
}
