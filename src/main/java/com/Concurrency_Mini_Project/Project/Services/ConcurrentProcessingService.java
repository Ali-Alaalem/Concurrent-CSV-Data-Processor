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
                    //i changed here synchronized (emp) to synchronized (System.out) because when i review what i did i catch that
                    //each emp will be assigned to a thread so no conflict between them but in the printing they will try to print all of them so its shared between them.
                    synchronized (System.out) {
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
//here also this is a new thing i learned which is await termination which will.
// do not continue this method until all threads inside this executor are finished.
//so it will wait maximum 1 minute. If threads finish earlier it will continue.
//and it should be after the shutdown if it was before it, it will wait forever.

        try {
            executor.awaitTermination(1, java.util.concurrent.TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return employees;
    }
}
