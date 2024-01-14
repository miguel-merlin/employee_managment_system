package com.gamma_computing.employees.controller;

import com.gamma_computing.employees.model.Employee;
import com.gamma_computing.employees.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/employee/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping
    public Employee getEmployee(@Param("employeeId") String employeeId) {
        return employeeService.getEmployee(Long.parseLong(employeeId));
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping
    public void deleteEmployee(String employeeId) {
        employeeService.deleteEmployee(Long.parseLong(employeeId));
    }
}
