package com.springes.demo.repo;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.springes.demo.elastic.model.Employee;


@Repository
public interface EmployeeRepositoryElastic extends ElasticsearchRepository<Employee, Integer> {
	
	List<Employee> findByEmpNameContainingOrLanguageContainingOrDepartmentContainingOrderByEmpIdAsc(String name, String language, String department);

	Employee findByEmpId(int empId);
}
