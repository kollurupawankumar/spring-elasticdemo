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
import com.springes.demo.dao.EmployeeElasticDAO;
import com.springes.demo.mysql.model.Employee;

@Controller
public class EmployeeCombinedController {
	
	@Autowired
	private EmployeeElasticDAO employeeElasticDao;
	
	@Autowired
	private EmployeeDBDAO employeeDao;
	
	
		
	@RequestMapping("/amazonIndex")
	public ModelAndView elasticHome(Model model) {
		try {
			model.addAttribute("employees", employeeElasticDao.getEmployees());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("amazonIndex");
	}
	
	
	
	/*Mirroring database to elastic search*/
	@RequestMapping(method = RequestMethod.POST, value = "/api/amazon/employee/add")
	public ModelAndView addEmployee(@ModelAttribute Employee employee) throws Exception {
		System.out.println("Came to this method");
		try {
			Employee emp = employeeDao.addEmployee(employee);
			employeeElasticDao.addEmployee(Employee.convertToElasticEmployee(emp, true));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/error");
		}
		return new ModelAndView("redirect:/amazonIndex");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/amazon/employee/delete")
	public ModelAndView deleteEmployee(int empId) throws Exception {
		try {
			employeeDao.deleteEmployee(empId);
			employeeElasticDao.deleteEmployee(empId);
		} catch (Exception e) {
			return new ModelAndView("redirect:/error");
		}
		return new ModelAndView("redirect:/amazonIndex");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/amazon/employee/edit")
	public String editEmployee(Model model, int empId) throws Exception {
		Employee employeeObj = null;
		try {
			employeeObj = employeeDao.editEmployee(empId);
			model.addAttribute("employees", employeeDao.getEmployees());
			model.addAttribute("employee", employeeObj);
			employeeElasticDao.editEmployee(empId);
		} catch (Exception e) {
			return "error";
		}
		return "amazonIndex";
	}
	
	/**End of mirroring to External DB*/

	
	@RequestMapping(method = RequestMethod.POST, value = "/api/amazon/employee/search")
	public String getElasticEmployee(Model model, @RequestParam String search) {
		try {
			model.addAttribute("employees", employeeElasticDao.getEmployee(search));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("employeeSearch", search);
		return "amazonIndex";
} 
}
