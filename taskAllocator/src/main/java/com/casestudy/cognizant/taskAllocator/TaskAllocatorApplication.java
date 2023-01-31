package com.casestudy.cognizant.taskAllocator;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.casestudy.cognizant.taskAllocator.model.Employee;
import com.casestudy.cognizant.taskAllocator.model.Job;
import com.casestudy.cognizant.taskAllocator.repository.EmployeeRepository;
import com.casestudy.cognizant.taskAllocator.repository.JobRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableFeignClients
public class TaskAllocatorApplication implements CommandLineRunner{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private JobRepository jobRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaskAllocatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee employee1 = new Employee("EMP1","Max","Jonathan","maxjonathan@gmail.com");
		Employee employee2 = new Employee("EMP2","John","Smith","johnsmith@gmail.com");
		Employee employee3 = new Employee("EMP3","Joe","Root","joeroot@gmail.com");
		Employee employee4 = new Employee("EMP4","Jofra","Archer","jofraarcher@gmail.com");
		
		Job task1 = new Job("TSK1","Planning","Find out the purpose of the project, who own the the information of project","Completed",LocalDate.of(2022, 11, 29));
		Job task2 = new Job("TSK2","Analysis","Gathering the information which is useful for the user","Completed",LocalDate.of(2022, 11, 28));
		Job task3 = new Job("TSK3","Design and Development","Find the type of layout is appropriate and design the project","In Progress",LocalDate.of(2022, 11, 26));
		Job task4 = new Job("TSK4","Testing","Testing of the project wheather it works properly or not.","In Progress",LocalDate.of(2022, 12, 30));
		Job task5 = new Job("TSK5","Implementation and Maintenance","Publish the site for the user and Monitor the project regularly for the maintenance ","Yet TO Start",LocalDate.of(2022, 11, 27));

		//Adding Tasks to employee-1
		employee1.getTasks().add(task1);
		employee1.getTasks().add(task2);
		employee1.getTasks().add(task3);
		employee1.getTasks().add(task4);
		employee1.getTasks().add(task5);
		
		//Adding Tasks to employee-2
		employee2.getTasks().add(task3);
		employee2.getTasks().add(task4);
		
		//Adding Tasks to employee-3
		employee3.getTasks().add(task3);
		employee3.getTasks().add(task4);
		
		//Adding Tasks to employee-4
		employee4.getTasks().add(task5);
		
		jobRepository.save(task1);
		jobRepository.save(task2);
		jobRepository.save(task3);
		jobRepository.save(task4);
		jobRepository.save(task5);
		
		employeeRepository.save(employee1);
		employeeRepository.save(employee2);
		employeeRepository.save(employee3);
		employeeRepository.save(employee4);
	}

}
