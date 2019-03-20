package com.pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.pack.model.Employee;
import com.pack.service.EmployeeService;

@Controller
public class EditEmployeeController {
	
	@Autowired
	private EmployeeService employeeService;  
	
	
@RequestMapping(value="/list")
	public String ListEmployee(ModelMap m)
{
	List<Employee> l= employeeService.listEmployee();
	m.addAttribute("employee",new Employee());
	m.addAttribute("employeeList",l);
	
return "employeeList";	
}

@RequestMapping(value="/add",method=RequestMethod.POST)
public String addEmployee(@ModelAttribute("employee")Employee e)
{
	employeeService.addEmployee(e);
	return "redirect:/list";
}


@RequestMapping(value="/delete/{eid}")
public String deleteEmployee(@PathVariable("eid")int empid)
{
employeeService.deleteEmployee(empid);
	

	return "redirect:/list";
}

@RequestMapping(value="/login")
public String login(ModelMap m)
{
	return "login";
}


@RequestMapping(value="/accessdenied",method=RequestMethod.GET)
public String loginerror(ModelMap model)
{
	model.addAttribute("error","true");
	return "denied";
}

@RequestMapping(value="/logout",method=RequestMethod.GET)
public String logout(ModelMap model)
{
	return "logout";
}
}
