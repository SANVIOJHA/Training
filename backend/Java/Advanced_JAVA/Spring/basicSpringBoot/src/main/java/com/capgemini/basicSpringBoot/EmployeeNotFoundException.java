package com.capgemini.basicSpringBoot;

public class EmployeeNotFoundException extends RuntimeException {
	
	EmployeeNotFoundException(String msg){
		super(msg);
	}
}
