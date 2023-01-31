package com.casestudy.cognizant.taskAllocator.exception;

@SuppressWarnings("unused")
public class InvalidEmployeeIdException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public InvalidEmployeeIdException() {}
	
	public InvalidEmployeeIdException(String msg) {
		super(msg);
		this.message = msg;
	}
}
