package com.aurionpro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aurionpro.entity.Employee;
import com.aurionpro.exception.EmployeeNotFoundException;
import com.aurionpro.repository.EmployeeRepository;

import jakarta.persistence.EntityManager;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
	    return employeeRepository.findById(id)
	            .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));
	}


	@Override
	public Employee addEmployee(Employee employee) {
		return (Employee) employeeRepository.save(employee);
	}

	@Override
	public String deleteEmployee(Integer id) {
	    Employee existingEmployee = employeeRepository.findById(id)
	            .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));

	    employeeRepository.deleteById(id);

	    return "Employee with id " + id + " deleted successfully.";
	}


	@Override
	public Employee updateEmployee(Employee employee) {
		return (Employee) employeeRepository.save(employee);
	}

}
