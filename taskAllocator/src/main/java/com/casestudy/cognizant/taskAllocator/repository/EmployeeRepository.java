package com.casestudy.cognizant.taskAllocator.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.casestudy.cognizant.taskAllocator.model.Employee;



@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String>{

}
