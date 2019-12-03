package com.zeeshan.service;

import java.util.List;

import com.zeeshan.domain.Employee;

public interface EmployeeService {

	public Employee createEmployee(Employee emp);

	public Employee getEmployee(Integer id);

	public List<Employee> getAllEmployee();

	public Employee updateEmployee(Employee emp, Integer id);

	public void deleteEmployee(Integer id);

}
