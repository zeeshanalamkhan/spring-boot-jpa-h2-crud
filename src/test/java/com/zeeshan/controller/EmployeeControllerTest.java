package com.zeeshan.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
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

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept("application/json");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(emp);
		String outputInJson = mvcResult.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

	}

	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);

	}
}
