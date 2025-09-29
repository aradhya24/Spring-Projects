package com.aurionpro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.entity.Employee;
import com.aurionpro.exception.EmployeeNotFoundException;
import com.aurionpro.service.EmployeeService;



@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
     
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}


	
	
	@GetMapping("/employee")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable int id) throws EmployeeNotFoundException {
		return employeeService.getEmployeeById(id);
	}
		
	
	@PostMapping("/employee") 
	public Employee addEmployee(@RequestBody Employee employee) throws EmployeeNotFoundException{
		return employeeService.addEmployee(employee);
	}
	
//	@PutMapping("/employee")
//	public Employee updateEmployee(@RequestBody Employee employee) {
//		return employeeService.addEmployee(employee);
//	}
	
	
	
	
	//patch mapping
	
	@PatchMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable int id, @RequestBody Employee partialEmployee) throws EmployeeNotFoundException{
		
		Employee existingEmployee = employeeService.getEmployeeById(id);
		
		if(partialEmployee.getFirstName() != null) {
			existingEmployee.setFirstName(partialEmployee.getFirstName());
		}
		
		if(partialEmployee.getLastName() != null) {
			existingEmployee.setLastName(partialEmployee.getLastName());
		}
		
		if(partialEmployee.getEmail() != null) {
			existingEmployee.setEmail(partialEmployee.getEmail());
		}
		
		return employeeService.updateEmployee(existingEmployee);
	}
	
	
	
	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException{
		return employeeService.deleteEmployee(employeeService.getEmployeeById(id).getId());
	}
	
}
