package com.casestudy.cognizant.taskAllocator.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.cognizant.taskAllocator.exception.InvalidEmployeeIdException;
import com.casestudy.cognizant.taskAllocator.exception.InvalidTokenException;
import com.casestudy.cognizant.taskAllocator.feign.AuthClient;
import com.casestudy.cognizant.taskAllocator.model.Employee;
import com.casestudy.cognizant.taskAllocator.model.Job;
import com.casestudy.cognizant.taskAllocator.service.EmployeeService;
import com.casestudy.cognizant.taskAllocator.service.JobService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class TaskAllocatorController {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(TaskAllocatorController.class);
	
	@Autowired
	private AuthClient authClient;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private JobService jobService;
	
	@GetMapping("/employeeList")
	public List<Employee> employeeList(@RequestHeader(name = "Authorization", required = true)String token){
		log.info("Inside getEmployee List Mapping");
		if(!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		log.info("Exiting getEmployee List Mapping");
		return employeeService.getEmployeeList();
	}
	
	@GetMapping("/soretdEmployee/{letter}")
	public List<Employee> soretdEmployeeList(@PathVariable("letter") String letter,@RequestHeader(name = "Authorization", required = true)String token){
		List<Employee> list = new ArrayList<>();
		log.info("Inside sortedEmployee List Mapping");
		if(!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		log.info("Exiting sortedEmployee List Mapping");
		list = employeeService.getEmployeeUsingLetter(letter);
		return employeeService.getSortedEmployeeList(list);
	}
	
	@GetMapping("/jobList")
	public List<Job> jobList(@RequestHeader(name = "Authorization", required = true)String token){
		log.info("Inside getJob List Mapping");

		 if(!authClient.authorizeTheRequest(token)) { 
			 throw new InvalidTokenException("Token is either expired or invalid"); 
		 }
		log.info("Exiting getJob List Mapping");
		return jobService.getJobList();
	}
	
	@GetMapping("/yesterDayTasks/{employeeId}")
	public Job getDayBeforeTaskOfEmployee(@PathVariable("employeeId") String employeeId, @RequestHeader(name = "Authorization", required = true)String token){
		log.info("Inside the day before task mapping");
		if(!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		log.info("Exiting the day before task mapping");
		return employeeService.getDayBeforeTask(employeeId);
	}
	
	@GetMapping("/employee/{employeeId}")
	public Employee getEmployee(@PathVariable("employeeId") String employeeId, @RequestHeader(name = "Authorization", required = true)String token) {
		log.info("Inside get Employee using employee id");
		
		if(!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		log.info("Exiting get Employee using employee id");
		//return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
		return employeeService.getEmployeeById(employeeId);
	}
	
	@GetMapping("/job/{jobId}")
	public Job getJob(@PathVariable("jobId") String jobId, @RequestHeader(name = "Authorization", required = true)String token) {
		log.info("Inside get Task using Task id");
		
		if(!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}

		log.info("Exiting get Task using Task id");
		return jobService.getJobById(jobId);
	}
	
	@PutMapping("/job/{jobId}")
	public Job updateJob(@PathVariable("jobId") String jobId, @RequestBody Job jobDetails) {
		
		return jobService.updateJob(jobId, jobDetails);
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable("employeeId") String employeeId, @RequestHeader(name = "Authorization", required = true)String token)throws InvalidEmployeeIdException {
		log.info("Inside deleteEmployee using id");
		if(!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		log.info("Exiting deleteEmployee using id");
		return employeeService.delete(employeeId);
	}
	
	@DeleteMapping("/job/{jobId}")
	public ResponseEntity<Map<String, Boolean>> deleteJob(@PathVariable("jobId") String jobId, @RequestHeader(name = "Authorization", required = true)String token){
		log.info("Inside deleteJob using id");
		if(!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}

		log.info("Exiting deleteJob using id");
		return jobService.delete(jobId);
	}
	
	@PostMapping("addEmployee")
	public String saveEmployee(@RequestBody Employee employee, @RequestHeader(name = "Authorization", required = true)String token) {
		log.info("Inside addEmployee Mapping");
		if(!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		employeeService.saveEmployee(employee);
		log.info("Exiting addEmployee Mapping");
		return employee.getId();
	}
	
	@PostMapping("/addJob")
	public String saveJob(@RequestBody Job job, @RequestHeader(name = "Authorization", required = true)String token) {
		log.info("Inside addJob Mapping");
		if(!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}

		jobService.saveJob(job);
		log.info("Exiting addJob Mapping");
		return job.getTaskId();
	}
	
	@PostMapping("/addtaskToEmployee/{employeeId}/{taskId}")
	public String addtaskToEmployee(@PathVariable("employeeId") String employeeId, @PathVariable("taskId") String taskId, @RequestHeader(name = "Authorization", required = true)String token) {
		log.info("Inside Add task to employee Mapping");
		if(!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		employeeService.addtaskToEmployee(employeeId, taskId);
		log.info("Exiting Add task to employee Mapping");
		return "Added Successfully";
	}
}
