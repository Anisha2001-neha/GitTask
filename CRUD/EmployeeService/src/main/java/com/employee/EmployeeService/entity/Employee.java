package com.employee.EmployeeService.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;


@Entity
public class Employee {
    @Id 
    private Long id;
    private String name;
    private String departmentId;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	
	}
