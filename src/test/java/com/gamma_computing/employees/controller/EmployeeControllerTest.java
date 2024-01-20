package com.gamma_computing.employees.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gamma_computing.employees.controller.EmployeeController;
import com.gamma_computing.employees.model.Employee;
import com.gamma_computing.employees.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTest {
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    private int SALARY = 5000;
    private int MONTHS_IN_YEAR = 12;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    private Employee createTestEmployee() {
        return new Employee(
                1L,
                "John",
                "Doe",
                "johndoe",
                LocalDate.now(),
                null,
                SALARY
        );
    }

    @Test
    void getAllEmployeesTest() throws Exception {
        Employee employee =createTestEmployee();
        List<Employee> employees = Arrays.asList(employee);
        given(employeeService.getAllEmployees()).willReturn(employees);

        mockMvc.perform(get("/api/v1/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(employee.getId()));
    }

    @Test
    void getEmployeeTest() throws Exception {
        Employee employee = createTestEmployee();
        given(employeeService.getEmployee(1L)).willReturn(employee);

        mockMvc.perform(get("/api/v1/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()));
    }

    @Test
    void createEmployeeTest() throws Exception {
        Employee employee = createTestEmployee();
        given(employeeService.createEmployee(any(Employee.class))).willReturn(employee);

        mockMvc.perform(
                post("/api/v1/employee/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(employee.getId()));
    }

    @Test
    void updateEmployeeTest() throws Exception {
        Employee employee = createTestEmployee();
        given(employeeService.updateEmployee(any(Employee.class))).willReturn(employee);

        mockMvc.perform(
                put("/api/v1/employee/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()));

    }
    @Test
    void deleteEmployeeTest() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/api/v1/employee/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployeeYearlySalaryTest() throws Exception {
        int yearlySalary = SALARY * MONTHS_IN_YEAR;
        given(employeeService.getEmployeeYearlySalary(1L)).willReturn(yearlySalary);

        mockMvc.perform(get("/api/v1/employee/salary/1"))
                .andExpect(status().isOk())
                .andExpect(content().string((String.valueOf(yearlySalary))));
    }
}
