package com.example.fetch_employees.exception;

public class EmployeeNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String abc) {
		super(abc);
	}

	
	
}
