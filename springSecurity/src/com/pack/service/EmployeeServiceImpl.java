package com.pack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.pack.dao.EmployeeDao;
import com.pack.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {
@Autowired
	private EmployeeDao employeeDao;


	@Transactional
	public void addEmployee(Employee e) {
		employeeDao.addEmployee(e);
		
		
	}
@Transactional
	@Override
	public List<Employee> listEmployee() {
		// TODO Auto-generated method stub
		List<Employee> l = employeeDao.listEmployee();
		return l;
	}
@Transactional
	@Override
	public void deleteEmployee(Integer id) {
		// TODO Auto-generated method stub
		employeeDao.deleteEmployee(id);
	}

	
	

}
