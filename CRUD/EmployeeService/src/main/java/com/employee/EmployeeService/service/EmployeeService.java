package com.employee.EmployeeService.service;

import java.util.List;
import java.util.Optional;

import com.employee.EmployeeService.entity.Employee;

public interface EmployeeService {
	Employee createEmployee(Employee employee);
	List<Employee> getAllEmployees();
	Optional<Employee> getEmployeeById(Long employeeId);
	Employee updateEmployee(Long employeeId, Employee employeeDetails);
	void deleteEmployee(Long employeeId);
}

