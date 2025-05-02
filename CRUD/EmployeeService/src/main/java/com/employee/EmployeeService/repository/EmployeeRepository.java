package com.employee.EmployeeService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.EmployeeService.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
