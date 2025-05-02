package com.department.DepartmentService.service;


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

import com.department.DepartmentService.entity.Department;
import com.department.DepartmentService.repository.DepartmentRepository;

public class DepartmentServiceImplTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDepartment() {
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        when(departmentRepository.save(department)).thenReturn(department);

        Department createdDepartment = departmentService.createDepartment(department);

        assertNotNull(createdDepartment);
        assertEquals("HR", createdDepartment.getName());
    }

    @Test
    public void testGetAllDepartments() {
        Department department1 = new Department();
        department1.setId(1L);
        department1.setName("HR");

        Department department2 = new Department();
        department2.setId(2L);
        department2.setName("Finance");

        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department1, department2));

        List<Department> departments = departmentService.getAllDepartments();

        assertEquals(2, departments.size());
        assertEquals("HR", departments.get(0).getName());
        assertEquals("Finance", departments.get(1).getName());
    }

    @Test
    public void testGetDepartmentById() {
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Optional<Department> foundDepartment = departmentService.getDepartmentById(1L);

        assertTrue(foundDepartment.isPresent());
        assertEquals("HR", foundDepartment.get().getName());
    }

    @Test
    public void testGetDepartmentById_NotFound() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Department> foundDepartment = departmentService.getDepartmentById(1L);

        assertFalse(foundDepartment.isPresent());
    }

    @Test
    public void testUpdateDepartment() {
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        Department updatedDetails = new Department();
        updatedDetails.setName("Finance");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(departmentRepository.save(department)).thenReturn(department);
}
}