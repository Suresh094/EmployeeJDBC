package com.suresh.MavenTest.Exceptions;


public class InvalidSalaryException extends RuntimeException {

	public InvalidSalaryException() {
		super();
	}

	public InvalidSalaryException(String message) {
		super(message);
	}

}
