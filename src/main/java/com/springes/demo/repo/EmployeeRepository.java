package com.springes.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springes.demo.mysql.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByEmpNameContainingOrLanguageContainingOrDepartmentContainingOrderByEmpIdAsc(String name, String language, String department);

	Employee findByEmpId(int empId);
}
