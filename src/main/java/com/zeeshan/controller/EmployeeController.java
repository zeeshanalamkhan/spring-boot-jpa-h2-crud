package com.zeeshan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zeeshan.domain.Employee;
import com.zeeshan.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@RequestMapping(value = "/add", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee emp) {

		empService.createEmployee(emp);
		return new ResponseEntity<>(emp, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/get/{id}", consumes = "application/json", produces = "application/json", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id) {

		return new ResponseEntity<>(empService.getEmployee(id), HttpStatus.FOUND);
	}

	@RequestMapping(value = "/get", consumes = "application/json", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployee() {

		return new ResponseEntity<>(empService.getAllEmployee(), HttpStatus.OK);
	}

	@RequestMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp, @PathVariable("id") Integer id) {

		return new ResponseEntity<>(empService.updateEmployee(emp, id), HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", consumes = "text/plain", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {

		empService.deleteEmployee(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
