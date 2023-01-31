package com.mfpe.Authorizationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfpe.Authorizationservice.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
	User findByUserName(String userName);
}
