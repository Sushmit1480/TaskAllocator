package com.casestudy.cognizant.taskAllocator.model;

//import java.util.ArrayList;
import java.util.HashSet;
//import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@Column(name="Id")
	private String Id;
	@Column(name="FirstName")
	private String firstName;
	@Column(name="LastName")
	private String lastName;
	@Column(name="Email")
	private String emailId;

	//@JoinColumn(name = "Tasks")
	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Job> tasks = new HashSet<>();
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String id, String firstName, String lastName, String emailId) {
		super();
		Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}
	
	
	public Employee(String id, String firstName, String lastName, String emailId, Set<Job> tasks) {
		super();
		Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.tasks = tasks;
	}

	
	
	public Set<Job> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Job> tasks) {
		this.tasks = tasks;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
}
