package com.gamma_computing.employees.repository;

import com.gamma_computing.employees.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends  JpaRepository<Employee, Long> {
}
