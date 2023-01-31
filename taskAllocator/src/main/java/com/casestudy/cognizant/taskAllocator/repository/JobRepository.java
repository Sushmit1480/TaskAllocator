package com.casestudy.cognizant.taskAllocator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.cognizant.taskAllocator.model.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, String>{
}
