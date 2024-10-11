package com.tcs.check_in_check_out_system;

import com.tcs.check_in_check_out_system.repository.EmployeeRepository;
import com.tcs.check_in_check_out_system.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CheckInCheckOutSystemApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Test
	public void testDeleteRecord() {
		Long idToDelete = 1L;

		// Mocking repository behavior

		// Call the delete method in the service
		employeeService.deleteRecord(idToDelete);

	}}
