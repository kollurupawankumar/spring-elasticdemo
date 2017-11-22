package com.springes.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springes.demo.elastic.model.Employee;
import com.springes.demo.repo.EmployeeRepositoryElastic;

@Service
public class EmployeeElasticDAO {
	
	@Autowired
	EmployeeRepositoryElastic repository1;
	
	
	public Employee addEmployee(Employee employee) throws Exception {
		Employee employeeObj=null;
		try {
			employeeObj = repository1.save(employee);
		} catch (Exception e) {
		}
		return employeeObj;
	}

	
	public List<Employee> getEmployees() throws Exception {
		Iterable<Employee> employeeObj=null;
		try {
			employeeObj = repository1.findByEmpNameContainingOrLanguageContainingOrDepartmentContainingOrderByEmpIdAsc("", "", "");
		} catch (Exception e) {
		}
		return (List<Employee>) employeeObj;
	}
	
	
	public List<Employee> getEmployee(String search) throws Exception {
		List<Employee> employeeObj=null;
		try {
			employeeObj = repository1.findByEmpNameContainingOrLanguageContainingOrDepartmentContainingOrderByEmpIdAsc( search, search, search);
		} catch (Exception e) {
		}
		return employeeObj;
	}
	
	
	public void deleteEmployee(int empId) throws Exception {
		try {
				repository1.delete(empId);
		} catch (Exception e) {
		}
	}
	
	
	public Employee editEmployee(int employeeId) throws Exception {
		Employee employeeObj=null;
		try {
			employeeObj=repository1.findByEmpId(employeeId);
		} catch (Exception e) {
		}
		return employeeObj;
}

}
