package com.casestudy.cognizant.taskAllocator.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="job")
public class Job {

	@Id
	@Column(name="TaskId")
	private String taskId;
	
	@Column(name="TaskName")
	private String taskName;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="CompletionDate")
	private LocalDate completion_date;

	public Job() {
		super();
	}

	public Job(String taskId, String taskName, String description, String status) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.description = description;
		this.status = status;
	}

	public Job(String taskId, String taskName, String description, String status, LocalDate completion_date) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.description = description;
		this.status = status;
		this.completion_date = completion_date;
	}
	
	public LocalDate getCompletion_date() {
		return completion_date;
	}

	public void setCompletion_date(LocalDate completion_date) {
		this.completion_date = completion_date;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Job [taskId=" + taskId + ", taskName=" + taskName + ", description=" + description + ", status="
				+ status + "]";
	}
	
	
}
