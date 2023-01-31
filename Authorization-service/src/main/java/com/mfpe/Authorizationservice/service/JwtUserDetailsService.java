package com.mfpe.Authorizationservice.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mfpe.Authorizationservice.model.MyUserDetails;
import com.mfpe.Authorizationservice.model.User;
import com.mfpe.Authorizationservice.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@SuppressWarnings("unused")
public class JwtUserDetailsService implements UserDetailsService{

	private static final Logger log=LoggerFactory.getLogger(JwtUserDetailsService.class);
	
	@Autowired
	private UserDAO userDAO;
	
	private PasswordEncoder bcryptEncoder; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		/** fetching user by userName, if user is null the throw exception, otherwise
		 * return user
		 */
		User user = userDAO.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		log.info("User found");
		log.info("user successfully located");
		
		org.springframework.security.core.userdetails.User u=new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),new ArrayList<>());
		
		return new MyUserDetails(user);
	}
	
}
