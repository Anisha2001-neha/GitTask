package com.employee.EmployeeService.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.employee.EmployeeService.entity.Employee;
import com.employee.EmployeeService.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllEmployees() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setDepartmentId("HR");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setDepartmentId("HR");

        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testGetEmployeeById_NotFound() throws Exception {
        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setDepartmentId("HR");

        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testCreateEmployee_InvalidInput() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setDepartmentId("HR");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Jane Doe");

        when(employeeService.updateEmployee(eq(1L), any(Employee.class))).thenReturn(updatedEmployee);

        mockMvc.perform(put("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    public void testUpdateEmployee_NotFound() throws Exception {
        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Jane Doe");

        when(employeeService.updateEmployee(eq(1L), any(Employee.class))).thenReturn(null);

        mockMvc.perform(put("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEmployee_NotFound() throws Exception {
        doThrow(new RuntimeException("Employee not found")).when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isNotFound());
    }
}
