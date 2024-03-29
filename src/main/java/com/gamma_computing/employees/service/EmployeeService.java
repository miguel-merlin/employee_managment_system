package com.gamma_computing.employees.service;

import com.gamma_computing.employees.model.Employee;

import java.util.List;
public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployee(Long employeeId);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long costumerId);

    int getEmployeeYearlySalary(Long employeeId);
}
