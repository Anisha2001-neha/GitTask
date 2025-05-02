package com.department.DepartmentService.service;



import java.util.List;
import java.util.Optional;

import com.department.DepartmentService.entity.Department;


public interface DepartmentService {
    Department createDepartment(Department department);
    List<Department> getAllDepartments();
    Optional<Department> getDepartmentById(Long departmentId);
    Department updateDepartment(Long departmentId, Department departmentDetails);
    void deleteDepartment(Long departmentId);
}
