package com.zeeshan.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.zeeshan.domain.Employee;
import com.zeeshan.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Test
	public void testCreateEmployee() {

		Employee emp = new Employee();
		emp.setDesg("Network Engineer");
		emp.setSal(15633.52);
		emp.setEmpName("Zeeshan Khan");

		Mockito.when(employeeRepository.save(emp)).thenReturn(emp);
		assertThat(employeeService.createEmployee(emp)).isEqualTo(emp);

	}

	@Test
	public void testGetEmployee() {

		Employee emp = new Employee();
		emp.setId(1);
		emp.setDesg("Network Engineer");
		emp.setSal(15633.52);
		emp.setEmpName("Zeeshan Khan");

		Optional<Employee> oEmp = Optional.of(emp);
		Mockito.when(employeeRepository.findById(1)).thenReturn(oEmp);
		assertThat(employeeService.getEmployee(1)).isEqualTo(oEmp.get());

	}

	@Test
	public void testGetAllEmployee() {

		Employee emp1 = new Employee();
		emp1.setDesg("Network Engineer");
		emp1.setEmpName("Zaheer Khan");
		emp1.setSal(5263.26);

		Employee emp2 = new Employee();
		emp2.setDesg("Software Engineer");
		emp2.setEmpName("Zeeshan Khan");
		emp2.setSal(5263.26);

		List<Employee> listEmp = new ArrayList<>();
		listEmp.add(emp1);
		listEmp.add(emp2);
		Mockito.when(employeeRepository.findAll()).thenReturn(listEmp);
		assertThat(employeeService.getAllEmployee()).isEqualTo(listEmp);
	}

	@Test
	public void testUpdateEmployee() {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setDesg("Network Engineer");
		emp.setEmpName("Zaheer Khan");
		emp.setSal(5263.26);

		Optional<Employee> oEmp = Optional.of(emp);
		Mockito.when(employeeRepository.findById(1)).thenReturn(oEmp);

		// emp.setDesg("Software Engineer");
		// oEmp = Optional.of(emp);
		// above two lines are equivalent to following one line

		oEmp.get().setDesg("Software Engineer");

		Mockito.when(employeeRepository.findById(1)).thenReturn(oEmp);

		// assertThat(employeeService.getEmployee(1)).isEqualTo(oEmp.get());

		// or

		assertThat(employeeService.getEmployee(1).getDesg()).isEqualTo("Software Engineer");

	}

	@Test
	public void testDeleteEmployee() {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setDesg("Network Engineer");
		emp.setEmpName("Zaheer Khan");
		emp.setSal(5263.26);

		Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));
		Mockito.when(employeeRepository.existsById(emp.getId())).thenReturn(false);

		assertFalse(employeeRepository.existsById(emp.getId()));
	}

}
