package com.zeeshan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeeshan.domain.Employee;
import com.zeeshan.exception.EmpNotExistsException;
import com.zeeshan.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;

	@Override
	public Employee createEmployee(Employee emp) {

		return empRepository.save(emp);

	}

	@Override
	public Employee getEmployee(Integer id) {

		Optional<Employee> emp = empRepository.findById(id);
		if (!emp.isPresent()) {
			throw new EmpNotExistsException("Employee with ID " + id + " does not exists");
		}
		return emp.get();
	}

	@Override
	public List<Employee> getAllEmployee() {

		List<Employee> listEmp = empRepository.findAll();

		if (listEmp.isEmpty()) {
			throw new EmpNotExistsException("No Employee Found");
		}

		return listEmp;
	}

	@Override
	public Employee updateEmployee(Employee emp, Integer id) {

		if (getEmployee(id) != null) {
			throw new EmpNotExistsException("Employee with ID " + id + " does not exists");
		}

		Employee emp1 = new Employee();
		emp1.setId(id);
		emp1.setEmpName(emp.getEmpName());
		emp1.setDesg(emp.getDesg());
		emp1.setSal(emp.getSal());

		return empRepository.save(emp1);
	}

	@Override
	public void deleteEmployee(Integer id) {

		if (getEmployee(id) != null) {
			throw new EmpNotExistsException("Employee with ID " + id + " does not exists");
		}

		empRepository.deleteById(id);

	}

}
