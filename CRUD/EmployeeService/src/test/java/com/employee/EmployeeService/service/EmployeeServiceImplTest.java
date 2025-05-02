package com.employee.EmployeeService.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employee.EmployeeService.entity.Employee;
import com.employee.EmployeeService.repository.EmployeeRepository;

public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setDepartmentId("HR");

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee createdEmployee = employeeService.createEmployee(employee);

        assertNotNull(createdEmployee);
        assertEquals("John Doe", createdEmployee.getName());
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setName("John Doe");
        employee1.setDepartmentId("HR");

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setName("Jane Doe");
        employee2.setDepartmentId("Finance");

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employees = employeeService.getAllEmployees();

        assertEquals(2, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
        assertEquals("Jane Doe", employees.get(1).getName());
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setDepartmentId("HR");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);

        assertTrue(foundEmployee.isPresent());
        assertEquals("John Doe", foundEmployee.get().getName());
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);

        assertFalse(foundEmployee.isPresent());
    }
}
   