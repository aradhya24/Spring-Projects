package com.aurionpro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aurionpro.dao.EmployeeDao;
import com.aurionpro.entity.Employee;
import com.aurionpro.exception.EmployeeNotFoundException;

import jakarta.persistence.EntityManager;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    
	private EmployeeDao employeeDao;
    
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		Employee employee = employeeDao.getEmployeeById(id);
	    if (employee == null) {
	        throw new EmployeeNotFoundException("Employee with id " + id + " not found");
	    }
	    return employee;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	public String deleteEmployee(Integer id) {
		return employeeDao.deleteEmployee(id);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeDao.save(employee);
	}
	
	

}
