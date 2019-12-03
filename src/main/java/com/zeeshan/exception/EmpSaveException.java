package com.zeeshan.exception;

public class EmpSaveException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmpSaveException() {
		super();
	}

	public EmpSaveException(String message) {
		super(message);
	}

	public EmpSaveException(Throwable cause) {
		super(cause);
	}

	public EmpSaveException(String message, Throwable cause) {
		super(message, cause);
	}

}
