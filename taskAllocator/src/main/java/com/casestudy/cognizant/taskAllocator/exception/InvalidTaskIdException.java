package com.casestudy.cognizant.taskAllocator.exception;

@SuppressWarnings("unused")
public class InvalidTaskIdException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public InvalidTaskIdException() {}
	
	public InvalidTaskIdException(String msg) {
		super(msg);
		this.message = msg;
	}

}
