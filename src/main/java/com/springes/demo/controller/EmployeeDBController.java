package com.springes.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springes.demo.dao.EmployeeDBDAO;
import com.springes.demo.mysql.model.Employee;

@Controller
public class EmployeeDBController {
	
	@Autowired
	private EmployeeDBDAO employeeDao;
	
	@RequestMapping("/dbIndex")
	public ModelAndView dbHome(Model model) {
		try {
			model.addAttribute("employees", employeeDao.getEmployees());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("dbIndex");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/db/employee/add")
	public ModelAndView addEmployee(@ModelAttribute Employee employee) throws Exception {
		System.out.println("Came to this method");
		try {
			employeeDao.addEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/error");
		}
		return new ModelAndView("redirect:/dbIndex");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/db/employee/delete")
	public ModelAndView deleteEmployee(int empId) throws Exception {
		try {
			employeeDao.deleteEmployee(empId);
		} catch (Exception e) {
			return new ModelAndView("redirect:/error");
		}
		return new ModelAndView("redirect:/dbIndex");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/db/employee/edit")
	public String editEmployee(Model model, int empId) throws Exception {
		Employee employeeObj = null;
		try {
			employeeObj = employeeDao.editEmployee(empId);
			model.addAttribute("employees", employeeDao.getEmployees());
			model.addAttribute("employee", employeeObj);
		} catch (Exception e) {
			return "error";
		}
		return "dbIndex";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/db/employee/search")
	public String getEmployee(Model model, @RequestParam String search) {
		System.out.println("came here ");
		System.out.println(search +":pawan");
		try {
			model.addAttribute("employees", employeeDao.getEmployee(search));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("employeeSearch", search);
		return "dbIndex";
}

}
