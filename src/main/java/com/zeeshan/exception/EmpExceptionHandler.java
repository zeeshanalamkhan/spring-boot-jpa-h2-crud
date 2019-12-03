package com.zeeshan.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmpExceptionHandler {

	@ExceptionHandler(value = EmpNotFoundException.class)
	public ResponseEntity<EmpError> empNotFound(EmpNotFoundException ex) {

		EmpError err = new EmpError(404, ex.getMessage(), new Date());
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = EmpNotExistsException.class)
	public ResponseEntity<EmpError> empNotExists(EmpNotExistsException ex) {

		EmpError err = new EmpError(404, ex.getMessage(), new Date());
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = EmpSaveException.class)
	public ResponseEntity<EmpError> empNotSaved(EmpSaveException ex) {

		EmpError err = new EmpError(400, ex.getMessage(), new Date());
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = EmpAlreadyExistsException.class)
	public ResponseEntity<EmpError> empAlreadySaved(EmpAlreadyExistsException ex) {

		EmpError err = new EmpError(409, ex.getMessage(), new Date());
		return new ResponseEntity<>(err, HttpStatus.CONFLICT);

	}

}
