package com.department.DepartmentService.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.DepartmentService.entity.Department;
import com.department.DepartmentService.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department departmentDetails) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department != null) {
            department.setName(departmentDetails.getName());
            return departmentRepository.save(department);
        }
        return null;
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
