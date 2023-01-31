package com.casestudy.cognizant.taskAllocator.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.casestudy.cognizant.taskAllocator.exception.InvalidEmployeeIdException;
import com.casestudy.cognizant.taskAllocator.exception.InvalidTaskIdException;
import com.casestudy.cognizant.taskAllocator.model.Employee;
import com.casestudy.cognizant.taskAllocator.model.Job;
import com.casestudy.cognizant.taskAllocator.repository.EmployeeRepository;
import com.casestudy.cognizant.taskAllocator.repository.JobRepository;


@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobService jobService;
	
	public List<Employee> getEmployeeList(){
		List<Employee> employees = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(employee -> employees.add(employee));
		return employees;
	}
	
	
	public List<Employee> getSortedEmployeeList(List<Employee> employees){
		Collections.sort(employees, new Comparator<Employee>() {
			public int compare(final Employee a, final Employee b) {
				return (a.getFirstName().compareTo(b.getFirstName()));
			}
		});
		return employees;
	}
	
	public List<Employee> getEmployeeUsingLetter(String alpha){
		String letter = alpha.toUpperCase();
		List<Employee> employees = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(employee -> employees.add(employee));
		Stream<Employee> stream = employees.stream().filter(employee 
				-> employee.getFirstName().startsWith(letter));
		return stream.collect(Collectors.toList());
	}
	
	public void addtaskToEmployee(String employeeId, String taskId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() 
				-> new InvalidEmployeeIdException("No Employee Present with ID = " + employeeId));
		Job job = jobRepository.findById(taskId).orElseThrow(() 
				-> new InvalidTaskIdException("No Task Present with ID = "+taskId));
		
		employee.getTasks().add(job);
		employeeRepository.save(employee);
	}
	
	public Job getDayBeforeTask(String id){
		Employee employee = employeeRepository.findById(id).orElseThrow(() 
				-> new InvalidEmployeeIdException("No Employee Present with ID = " + id));
		Set<Job> tasks = employee.getTasks();
		return jobService.getYesterDayTask(tasks);
	}
	
	public Employee getEmployeeById(String id) {
		Employee emp = employeeRepository.findById(id).orElseThrow(() 
				-> new InvalidEmployeeIdException("No Employee Present with ID = " + id));
		return emp;
	}
	
	/*
	 * public void delete(String id) { employeeRepository.deleteById(id); }
	 */
	public ResponseEntity<Map<String, Boolean>> delete(String id){
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new InvalidEmployeeIdException("No Employee Present with ID = "+id));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
}
