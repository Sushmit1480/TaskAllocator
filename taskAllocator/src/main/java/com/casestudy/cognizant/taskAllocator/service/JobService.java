package com.casestudy.cognizant.taskAllocator.service;

import java.time.LocalDate;
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

import com.casestudy.cognizant.taskAllocator.exception.InvalidTaskIdException;
import com.casestudy.cognizant.taskAllocator.model.Job;
import com.casestudy.cognizant.taskAllocator.repository.JobRepository;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	public List<Job> getJobList(){
		List<Job> jobs = new ArrayList<Job>();
		jobRepository.findAll().forEach(job -> jobs.add(job));
		return jobs;
	}
	
	public Job getYesterDayTask(Set<Job> job){
		//LocalDate today = LocalDate.now();
		List<Job> jobs = new ArrayList<>(job);
		
		/*
		 * Stream<Job> stream = tasks.stream().filter(task ->
		 * task.getCompletion_date().isBefore(today)); List<Job> jobs =
		 * stream.collect(Collectors.toList());
		 */
		
		Collections.sort(jobs, new Comparator<Job>() {
			public int compare(final Job a, final Job b) {
				return (b.getCompletion_date().compareTo(a.getCompletion_date()));
			}
		});
		return jobs.get(1);
	}
	
	public Job getJobById(String id) {
		return jobRepository.findById(id).orElseThrow(() -> new InvalidTaskIdException("No Task Present with ID = "+id));
	}
	
	public ResponseEntity<Map<String, Boolean>> delete(String id) {
		Job job = jobRepository.findById(id).orElseThrow(() -> new InvalidTaskIdException("No Task Present with ID = "+id));
		jobRepository.delete(job);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	public void saveJob(Job job) {
		jobRepository.save(job);
	}
	
	public Job updateJob(String id, Job jobdetails) {
		Job job = jobRepository.findById(id).orElseThrow(() -> new InvalidTaskIdException("No Task Present with ID = "+id));
		job.setTaskName(jobdetails.getTaskName());
		job.setDescription(jobdetails.getDescription());
		job.setStatus(jobdetails.getStatus());
		job.setCompletion_date(jobdetails.getCompletion_date());;
		Job updatedJob = jobRepository.save(job);
		return updatedJob;
	}
}
