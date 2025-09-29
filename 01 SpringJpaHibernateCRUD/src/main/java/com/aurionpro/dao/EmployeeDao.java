package com.aurionpro.dao;

import java.util.List;

import com.aurionpro.entity.Employee;

public interface EmployeeDao {
	
	public void createEmployee(Employee employee);
	
	public Employee getEmployeeById(Integer id);
	
	public List<Employee> getAllEmployee();
	
	public void updateEmployeeName(Integer id , String name);
	
	public void deleteEmployeeById(Integer id);

}
