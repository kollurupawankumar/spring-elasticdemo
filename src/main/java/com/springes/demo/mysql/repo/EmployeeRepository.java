package com.springes.demo.mysql.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springes.demo.mysql.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByEmpNameContainingOrLanguageContainingOrDepartmentContainingOrderByEmployeeIdAsc(String name, String language, String department);

	Employee findByEmpId(int empId);
}
