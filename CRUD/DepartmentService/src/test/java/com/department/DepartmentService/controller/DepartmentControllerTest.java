package com.department.DepartmentService.controller;


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

import com.department.DepartmentService.entity.Department;
import com.department.DepartmentService.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllDepartments() throws Exception {
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        when(departmentService.getAllDepartments()).thenReturn(Arrays.asList(department));

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("HR"));
    }

    @Test
    public void testGetDepartmentById() throws Exception {
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        when(departmentService.getDepartmentById(1L)).thenReturn(Optional.of(department));

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("HR"));
    }

    @Test
    public void testGetDepartmentById_NotFound() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateDepartment() throws Exception {
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        when(departmentService.createDepartment(any(Department.class))).thenReturn(department);

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("HR"));
    }

    @Test
    public void testCreateDepartment_InvalidInput() throws Exception {
        Department department = new Department();
        department.setId(1L);

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        Department updatedDepartment = new Department();
        updatedDepartment.setName("Finance");

        when(departmentService.updateDepartment(eq(1L), any(Department.class))).thenReturn(updatedDepartment);

        mockMvc.perform(put("/departments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDepartment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Finance"));
    }

    @Test
    public void testUpdateDepartment_NotFound() throws Exception {
        Department updatedDepartment = new Department();
        updatedDepartment.setName("Finance");

        when(departmentService.updateDepartment(eq(1L), any(Department.class))).thenReturn(null);

        mockMvc.perform(put("/departments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDepartment)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        doNothing().when(departmentService).deleteDepartment(1L);

        mockMvc.perform(delete("/departments/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteDepartment_NotFound() throws Exception {
        doThrow(new RuntimeException("Department not found")).when(departmentService).deleteDepartment(1L);

        mockMvc.perform(delete("/departments/1"))
                .andExpect(status().isNotFound());
    }
}
