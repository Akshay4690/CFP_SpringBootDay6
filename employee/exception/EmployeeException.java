package com.demo.employee.exception;

public class EmployeeException extends RuntimeException {

	private String message;
	
	public EmployeeException() {
		super();
	}
	
	public EmployeeException(String message) {
		super(message);
	}

}
