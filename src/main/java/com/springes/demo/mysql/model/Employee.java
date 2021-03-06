package com.springes.demo.mysql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Employee {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;
	private String empName;
	private double salary;
	private String language;
	private String department;
	
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public static com.springes.demo.elastic.model.Employee convertToElasticEmployee(Employee e, boolean needId){
		com.springes.demo.elastic.model.Employee ele = new com.springes.demo.elastic.model.Employee();
		ele.setDepartment(e.getDepartment());
		if (needId)	ele.setEmpId(e.getEmpId());
		ele.setEmpName("Elastic :"+e.getEmpName());
		ele.setLanguage(e.getLanguage());
		ele.setSalary(e.getSalary());
		return ele;
	}
	

}
