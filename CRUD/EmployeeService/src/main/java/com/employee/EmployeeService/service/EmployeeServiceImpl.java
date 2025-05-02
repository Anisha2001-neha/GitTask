package com.employee.EmployeeService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.EmployeeService.entity.Employee;
import com.employee.EmployeeService.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long employeeId) {
		return employeeRepository.findById(employeeId);
	}

	@Override
	public Employee updateEmployee(Long employeeId, Employee employeeDetails) {
		Employee employee = employeeRepository.findById(employeeId).orElse(null);
		if (employee != null) {
			employee.setName(employeeDetails.getName());
			employee.setDepartmentId(employeeDetails.getDepartmentId());
			return employeeRepository.save(employee);
		}
		return null;
		}

	@Override
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}
}


