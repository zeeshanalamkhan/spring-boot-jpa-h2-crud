package com.zeeshan.exception;

public class EmpNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmpNotFoundException() {
		super();
	}

	public EmpNotFoundException(String message) {
		super(message);
	}

	public EmpNotFoundException(Throwable cause) {
		super(cause);
	}

	public EmpNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
