package com.zeeshan.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.zeeshan.domain.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void testSaveEmployee() {

		Employee emp = getEmployee();
		Employee savedInDB = entityManager.persist(emp);
		Employee getFromDB = employeeRepository.findById(savedInDB.getId()).get();
		assertThat(getFromDB).isEqualTo(savedInDB);

	}

	@Test
	public void testgetEmployee() {

		Employee emp = new Employee();
		emp.setDesg("Network Engineer");
		emp.setSal(15633.52);
		emp.setEmpName("Zeeshan Khan");

		Employee savedInDB = entityManager.persist(emp);

		Employee getEmpFromDB = employeeRepository.getOne(savedInDB.getId());
		assertThat(savedInDB).isEqualTo(getEmpFromDB);

	}

	@Test
	public void testgetAllEmployee() {

		Employee emp = new Employee();
		emp.setDesg("Network Engineer");
		emp.setSal(15633.52);
		emp.setEmpName("Zeeshan Khan");

		Employee emp1 = new Employee();
		emp1.setDesg("Software Engineer");
		emp1.setSal(7633.52);
		emp1.setEmpName("Zaheer Khan");

		entityManager.persist(emp);
		entityManager.persist(emp1);

		List<Employee> allEmployeeFromDB = employeeRepository.findAll();
		List<Employee> empList = new ArrayList<Employee>();

		for (Employee e : allEmployeeFromDB) {
			empList.add(e);
		}
		assertThat(empList.size()).isEqualTo(2);
	}

	@Test
	public void testDeleteEmployee() {

		Employee emp = new Employee();
		emp.setDesg("Network Engineer");
		emp.setSal(15633.52);
		emp.setEmpName("Zeeshan Khan");

		Employee emp1 = new Employee();
		emp1.setDesg("Software Engineer");
		emp1.setSal(7633.52);
		emp1.setEmpName("Zaheer Khan");

		Employee e1 = entityManager.persist(emp);
		entityManager.persist(emp1);

		employeeRepository.deleteById(e1.getId());

		Iterable<Employee> allEmployeeFromDB = employeeRepository.findAll();
		List<Employee> empList = new ArrayList<Employee>();

		for (Employee e : allEmployeeFromDB) {
			empList.add(e);
		}
		assertThat(empList.size()).isEqualTo(1);
	}

	@Test
	public void testupdateEmployee() {

		Employee emp = new Employee();
		emp.setDesg("Network Engineer");
		emp.setSal(15633.52);
		emp.setEmpName("Zeeshan Khan");

		Employee savedInDB = entityManager.persist(emp);

		savedInDB.setDesg("Java Developer");
		entityManager.persist(savedInDB);
		Employee getEmpFromDB = employeeRepository.getOne(savedInDB.getId());
		assertThat(getEmpFromDB.getDesg()).isEqualTo("Java Developer");

	}

	private Employee getEmployee() {

		Employee emp = new Employee();
		emp.setDesg("Network Engineer");
		emp.setSal(15633.52);
		emp.setEmpName("Zeeshan Khan");
		return emp;

	}

}
