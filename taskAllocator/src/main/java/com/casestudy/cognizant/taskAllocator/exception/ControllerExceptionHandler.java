package com.casestudy.cognizant.taskAllocator.exception;

import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.casestudy.cognizant.taskAllocator.model.ErrorMessage;

import ch.qos.logback.classic.Logger;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	@ExceptionHandler(value=InvalidEmployeeIdException.class)
	public ResponseEntity<ErrorMessage> invalidEmployeeId(InvalidEmployeeIdException e, WebRequest request){
		log.info("Inside Invalid Employee Id Exception Handler");
		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), e.getMessage(), request.getDescription(false));
		log.info("Exiting Invalid Employee Id Exception Handler");
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = InvalidTaskIdException.class)
	public ResponseEntity<ErrorMessage> invalidTaskId(InvalidTaskIdException e, WebRequest request){
		log.info("Inside Invalid Task Id Exception Handler");
		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), e.getMessage(), request.getDescription(false));
		log.info("Exiting Invalid Task Id Exception Handler");
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value=InvalidTokenException.class)
	public ResponseEntity<ErrorMessage> invalidTokenExceptionHandling(InvalidTokenException e, WebRequest request){
		log.info("Inside Invalid Token Exception Handler");
		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), "Token is either expired or invalid...", request.getDescription(false));
		log.info("Exiting Invalid Token Exception Handler");
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorMessage> feignExceptionHandling(FeignException e, WebRequest request){
		log.info("Inside Invalid token exception handler");
		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), "Token is either expired or invalid...", request.getDescription(false));
		log.info("Exiting Invalid Token Exception Handling");
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
}