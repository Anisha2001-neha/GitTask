package com.department.DepartmentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.department.DepartmentService.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

