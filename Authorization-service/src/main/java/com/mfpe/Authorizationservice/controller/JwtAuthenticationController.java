package com.mfpe.Authorizationservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
/*import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;*/
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;
import com.mfpe.Authorizationservice.config.JwtTokenUtil;
//import com.mfpe.Authorizationservice.dto.AuthenticationRequestDTO;
import com.mfpe.Authorizationservice.exception.AuthorisationException;
import com.mfpe.Authorizationservice.model.JwtRequest;
import com.mfpe.Authorizationservice.model.JwtResponse;
//import com.mfpe.Authorizationservice.model.JwtResponse;
import com.mfpe.Authorizationservice.service.JwtUserDetailsService;
//
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin
@Api(value = "Login and Validation endpoints for Authorization Service")
@SuppressWarnings("unused")
public class JwtAuthenticationController {
	
	private static final Logger log=LoggerFactory.getLogger(JwtAuthenticationController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	/**
	 * @param authenticationRequest
	 * @return
	 * @throws AuthorisationException 

	 * @throws Exception
	 * 
	 *
	 */
	@PostMapping(value="/login",headers = {
    "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "customerLogin", notes = "takes customer credentials and generates the unique JWT for each customer", httpMethod = "POST", response = ResponseEntity.class)
	public ResponseEntity<?> createAuthenticationToken(
			@ApiParam(name = "customerLoginCredentials", value = "Login credentials of the Customer")
			@RequestBody JwtRequest authenticationRequest
			) throws AuthorisationException{
		
		JwtRequest authRequest=new JwtRequest();
		System.out.println(authenticationRequest.getUserName());
		authRequest.setUserName(authenticationRequest.getUserName());
		authRequest.setPassword(authenticationRequest.getPassword());
		log.info("BEGIN - [login(customerLoginCredentials)]");
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());

		log.debug("{}", userDetails);
		//authRequest.getPassword()
		if(userDetails.getPassword().equals(authRequest.getPassword())) {
			log.info("END - [login(customerLoginCredentials)]");
			String token = jwtTokenUtil.generateToken(userDetails);
			System.out.println(jwtTokenUtil.generateToken(userDetails));


			return ResponseEntity.ok(new JwtResponse(token));
		}

		log.info("END - [login(customerLoginCredentials)]");

		throw new AuthorisationException("Invalid Username or Password");
	}
	
	/*
	@SuppressWarnings("unused")
	private Authentication  authenticate(String userName, String password) throws AuthorisationException {
		try {
			System.out.println("Inside authenticate Method==================================");
			Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			System.out.println("Authentication Successful.....");
			System.out.println(auth.getCredentials()+"+++++++++++++++++++++++++++++++++");
			return auth;
			
		} catch (DisabledException e) {
			throw new AuthorisationException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new AuthorisationException("INVALID_CREDENTIALS");
		}
		
	}
	*/
	
	/**
	 * @param requestTokenHeader
	 * @return
	 */
	@PostMapping(value = "/authorize")
	public boolean authorizeTheRequest(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) {
		System.out.println("Inside authorize =============="+requestTokenHeader);
		String jwtToken = null;
		String userName = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			System.out.println("JWT Tocken ======================="+jwtToken);
			try {
				userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException | ExpiredJwtException e) {
				return false;
			}
		}
		return userName != null;

	}

	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("auth-Ok", HttpStatus.OK);
	}

}