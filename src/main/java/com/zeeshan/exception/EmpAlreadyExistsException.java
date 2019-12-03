package com.zeeshan.exception;

public class EmpAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmpAlreadyExistsException() {
		super();
	}

	public EmpAlreadyExistsException(String message) {
		super(message);
	}

	public EmpAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public EmpAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
