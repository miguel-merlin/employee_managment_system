package com.gamma_computing.employees.service;

import com.gamma_computing.employees.model.Employee;
import com.gamma_computing.employees.repository.EmployeeRepository;
import com.gamma_computing.employees.service.EmployeeServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private Long employeeId;
    private int salary = 3000;

    @Before
    public void setUp() throws Exception {
        employeeId = 1L;
        employee = new Employee(
                employeeId,
                "John",
                "Doe",
                "johndoe",
                LocalDate.now(),
                null,
                salary
        );

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(employee);
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testGetEmployeeSuccess() {
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployee(employeeId);

        assertNotNull(result);
        assertEquals(employeeId, result.getId());
        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetEmployeeNotFound() {
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        employeeService.getEmployee(employeeId);
    }

    @Test
    public void testCreateEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertNotNull(result);
        assertEquals(employee.getName(), result.getName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testUpdateEmployee() {
        when(employeeRepository.saveAndFlush(any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.updateEmployee(employee);

        assertNotNull(result);
        verify(employeeRepository, times(1)).saveAndFlush(any(Employee.class));
    }

    @Test
    public void testDeleteEmployee() {
        doNothing().when(employeeRepository).deleteById(employeeId);

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    public void testGetEmployeeYearlySalary() {
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        int yearlySalary = employeeService.getEmployeeYearlySalary(employeeId);

        assertEquals(employee.getMonthlySalary() * 12, yearlySalary);
        verify(employeeRepository, times(1)).findById(employeeId);
    }

}
