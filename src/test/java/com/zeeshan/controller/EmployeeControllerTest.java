package com.zeeshan.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeeshan.domain.Employee;
import com.zeeshan.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@Test
	public void testSaveEmployee() throws Exception {

		Employee emp = new Employee();

		emp.setId(1);
		emp.setDesg("Network Engineer");
		emp.setEmpName("Rajesh Kumar");
		emp.setSal(52563.23);

		String inputInJson = this.mapToJson(emp);
		String URI = "/add";
		Mockito.when(employeeService.createEmployee(Mockito.any(Employee.class))).thenReturn(emp);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept("application/json").content(inputInJson)
				.contentType("application/json");

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		String outputInJson = mockHttpServletResponse.getContentAsString();
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.CREATED.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testGetEmployee() throws Exception {

		Employee emp = new Employee(1, "Ravi Teja", "Hardware Engineer", 56324.50);

		String URI = "/get/1";

		Mockito.when(employeeService.getEmployee(Mockito.anyInt())).thenReturn(emp);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept("application/json")
				.contentType("application/json");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(emp);
		String outputInJson = mvcResult.getResponse().getContentAsString();

		assertThat(outputInJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.FOUND.value(), mvcResult.getResponse().getStatus());

	}

	@Test
	public void getAllEmployee() throws Exception {

		Employee emp1 = new Employee(1, "Allu Arjun", "Actor", 456930.50);
		Employee emp2 = new Employee(2, "Dharmendra", "Actor", 56230.60);

		List<Employee> listEmp = new ArrayList<>();
		listEmp.add(emp1);
		listEmp.add(emp2);

		String URI = "/get";

		Mockito.when(employeeService.getAllEmployee()).thenReturn(listEmp);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept("application/json")
				.contentType("application/json");

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(listEmp);
		String outputInJson = mvcResult.getResponse().getContentAsString();

		assertThat(outputInJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());

	}

	@Test
	public void testUpdateEmployee() throws Exception {

		Employee emp = new Employee(1, "Krishna", "Network Engineer", 56230.40);

		String URI = "/update/1";
		String inputInJson = this.mapToJson(emp);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept("application/json").content(inputInJson)
				.contentType("application/json");
		emp.setDesg("Java Developer");

		Mockito.when(employeeService.updateEmployee(Mockito.any(Employee.class), Mockito.anyInt())).thenReturn(emp);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(emp);
		String outputInJson = mvcResult.getResponse().getContentAsString();
		assertThat(expectedJson).isEqualTo(outputInJson);
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}

	@Test
	public void testDeleteEmployee() throws Exception {

		Employee emp = new Employee(1, "Rajesh", "Java Developer", 456203.40);
		String URI = "/delete/1";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).contentType("text/plain");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
	}

	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);

	}
}
