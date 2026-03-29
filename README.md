# Concurrent CSV Data Processor

This project is a **Spring Boot Java application** that reads employee data from a CSV file and processes salary increments concurrently using multithreading techniques such as **thread pooling, semaphores, and synchronized blocks**. The goal of the project is to demonstrate correct use of concurrency control mechanisms while applying business rules on employee salaries.

The application reads a CSV file containing employee information: **name, salary, joined date, role, and percentage of completed projects**. Each employee's salary is updated based on the following rules:

- Employees with less than 60% project completion do not receive any increase.
- Salary increases by role:
    - **Director**: 5%
    - **Manager**: 2%
    - **Employee**: 1%
- Additional **2% increase per year** worked after the first year.

---

## Project Setup Instructions

1. Ensure **Java 17+** and **Maven** are installed on your system.
2. Place the `Employees.csv` file in the root directory of the project. The CSV file should have the following format:


Example:
- Ali,1000,2020-05-10,Manager,80
- Sara,900,2022-01-15,Employee,70



3. Build and run the Spring Boot application using your IDE or command line.
4. Trigger the salary processing via the provided controller using PostMan.
5. Check the console output for each employee’s **old and new salary**.

---

## Code Documentation

The project has three main components:

### 1. `CsvReaderService`
- Reads the CSV file, parses each line, converts it into an `Employee` object, and returns a list of employees.
- Handles errors if the file is missing or contains invalid data.

### 2. `SalaryCalcSarvice`
- Calculates the new salary for each employee according to their **role**, **project completion percentage**, and **years worked**.


### 3. `ConcurrentProcessingService`
- Processes the employee list concurrently using `ExecutorService` with a fixed thread pool.
- Uses a **Semaphore** to limit the number of threads entering the critical section simultaneously.
- A `synchronized(System.out)` block ensures console output is not garbled when multiple threads print at the same time.
- After submitting all tasks, `executor.shutdown()` and `executor.awaitTermination()` ensure all threads complete before returning the updated employee list.

---

## Project Implementation Summary

- Implements **multithreading** and **concurrency control** to safely update employee salaries.
- **Thread pooling** allows multiple employees to be processed simultaneously.
- **Semaphores** control concurrent access to critical sections.
- **Synchronized blocks** protect shared resources, such as console output.
- `awaitTermination` ensures the main thread waits until all worker threads finish, preventing incomplete processing.
- Salary rules are applied correctly based on **role**, **project completion**, and **years worked**.

---

## Outcomes

- Employee salaries are processed **concurrently and correctly**.
- **Concurrency control** ensures data integrity.
- Shared and non-shared resources are properly managed.
- Demonstrates practical understanding of **multithreading, semaphores, synchronized blocks, and thread lifecycle management in Java**.
