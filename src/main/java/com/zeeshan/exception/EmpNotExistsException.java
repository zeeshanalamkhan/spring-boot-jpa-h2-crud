package com.zeeshan.exception;

public class EmpNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmpNotExistsException() {
		super();
	}

	public EmpNotExistsException(String message) {
		super(message);
	}

	public EmpNotExistsException(Throwable cause) {
		super(cause);
	}

	public EmpNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
