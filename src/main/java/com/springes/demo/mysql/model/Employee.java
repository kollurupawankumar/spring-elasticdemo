package com.springes.demo.mysql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class Employee {
	
	private int empId;
	private String empName;
	private double salary;
	private String languge;
	private int department;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	public String getLanguge() {
		return languge;
	}
	public void setLanguge(String languge) {
		this.languge = languge;
	}
	public int getDepartment() {
		return department;
	}
	public void setDepartment(int department) {
		this.department = department;
	}
	
	
	

}
