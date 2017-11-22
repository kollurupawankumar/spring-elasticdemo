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
	public ModelAndView jpaHome(Model model) {
		try {
			model.addAttribute("employees", employeeDao.getEmployees());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("dbIndex");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/db/employee/add")
	public ModelAndView addJpaEmployee(@ModelAttribute Employee employee) throws Exception {
		try {
			employeeDao.addEmployee(employee);
		} catch (Exception e) {
			return new ModelAndView("redirect:/error");
		}
		return new ModelAndView("redirect:/dbIndex");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/db/employee/delete")
	public ModelAndView deleteJpaEmployee(int empId) throws Exception {
		try {
			employeeDao.deleteEmployee(empId);
		} catch (Exception e) {
			return new ModelAndView("redirect:/error");
		}
		return new ModelAndView("redirect:/dbIndex");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/db/employee/edit")
	public String editJpaEmployee(Model model, int employeeId) throws Exception {
		Employee employeeObj = null;
		try {
			employeeObj = employeeDao.editEmployee(employeeId);
			model.addAttribute("employees", employeeDao.getEmployees());
			model.addAttribute("employee", employeeObj);
		} catch (Exception e) {
			return "error";
		}
		return "dbIndex";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/jpa/employee/search")
	public String getJpaEmployee(Model model, @RequestParam String search) {
		try {
			model.addAttribute("employees", employeeDao.getEmployee(search));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("employeeSearch", search);
		return "dbIndex";
}

}
