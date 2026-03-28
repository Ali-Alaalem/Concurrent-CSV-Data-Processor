package com.Concurrency_Mini_Project.Project;

import com.Concurrency_Mini_Project.Project.Models.Employee;
import com.Concurrency_Mini_Project.Project.Services.CsvReaderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
	}

}
