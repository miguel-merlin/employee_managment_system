package com.gamma_computing.employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamma_computing.employees.controller.EmployeeController;
import com.gamma_computing.employees.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class EmployeeControllerTest {
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void getAllEmployeesTest() throws Exception {}

    @Test
    void getEmployeeTest() throws Exception {}

    @Test
    void createEmployeeTest() throws Exception {}

    @Test
    void updateEmployeeTest() throws Exception {}

    @Test
    void deleteEmployeeTest() throws Exception {}

    @Test
    void getEmployeeYearlySalaryTest() throws Exception {}
}
