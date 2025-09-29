package com.aurionpro.dao;

import java.util.List;

import com.aurionpro.entity.Employee;

public interface EmployeeDao {
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(Integer id);
	
	public Employee save(Employee employee);
	
	public String deleteEmployee(Integer id);

}
