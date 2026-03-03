package com.capgemini.basicSpringBoot;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ApplicationException {
/////this one is only for employeenotfound
	@ExceptionHandler(EmployeeNotFoundException.class)
	public String handleException(EmployeeNotFoundException e) {
		return e.getMessage();
	}
	
@ExceptionHandler(Exception.class)
	public String handleException1(Exception e) {
		return e.getMessage();
	}


}
