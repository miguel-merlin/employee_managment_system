package com.gamma_computing.employees.service;

import com.gamma_computing.employees.model.Employee;
import com.gamma_computing.employees.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl  implements EmployeeService {
    int MONTHS_IN_YEAR = 12;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving employees: ", e);
        }
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        try {
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            if (!employee.isPresent()) {
                throw new EntityNotFoundException("Employee with id " + employeeId + " not found");
            }
            return employee.get();
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving employee with id: " + employeeId.toString(), e);
        }
    }

    @Override
    public Employee createEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            throw new RuntimeException("Error creating creating employee", e);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try {
            return employeeRepository.saveAndFlush(employee);
        } catch (Exception e) {
            throw new RuntimeException("Error updating employee");
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        try {
            employeeRepository.deleteById(employeeId);
        } catch (Exception e) {
            throw  new RuntimeException("Error deleting employee");
        }
    }

    @Override
    public int getEmployeeYearlySalary(Long employeeId) {
        try {
            Employee employee = getEmployee(employeeId);
            return employee.getMonthlySalary() * MONTHS_IN_YEAR;
        } catch (Exception e) {
            throw new RuntimeException("Error calculating yearly salary");
        }
    }
}
