package com.springes.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springes.demo.mysql.model.Employee;
import com.springes.demo.mysql.repo.EmployeeRepository;

@Service
public class EmployeeDBDAO {

	@Autowired
	EmployeeRepository repository;

	public Employee addEmployee(Employee employee) throws Exception {
		Employee employeeObj = null;
		try {
			employeeObj = repository.save(employee);
		} catch (Exception e) {
		}
		return employeeObj;
	}

	public List<Employee> getEmployees() throws Exception {
		Iterable<Employee> employeeObj = null;
		try {
			employeeObj = repository.findAll();
		} catch (Exception e) {
		}
		return (List<Employee>) employeeObj;
	}

	public List<Employee> getEmployee(String search) throws Exception {
		List<Employee> employeeObj = null;
		try {
			employeeObj = repository
					.findByEmpNameContainingOrLanguageContainingOrDepartmentContainingOrderByEmployeeIdAsc(search,
							search, search);
		} catch (Exception e) {
		}
		return employeeObj;
	}

	public void deleteEmployee(int employeeId) throws Exception {
		try {
			repository.delete(employeeId);
		} catch (Exception e) {
		}
	}

	public Employee editEmployee(int employeeId) throws Exception {
		Employee employeeObj = null;
		try {
			employeeObj = repository.findByEmpId(employeeId);
		} catch (Exception e) {
		}
		return employeeObj;
	}
}
