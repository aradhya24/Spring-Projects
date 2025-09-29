package com.aurionpro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dto.EmployeePatchDto;
import com.aurionpro.dto.EmployeeRequestDto;
import com.aurionpro.dto.EmployeeResponseDto;
import com.aurionpro.service.EmployeeService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping("")
	public ResponseEntity<EmployeeResponseDto> addEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
		return ResponseEntity.ok(employeeService.addEmployee(employeeRequestDto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> get(@PathVariable Integer id) {
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}

	@GetMapping
	public ResponseEntity<List<EmployeeResponseDto>> list() {
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}

	@PatchMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> updateProfile(@PathVariable Integer id,
			@Valid @RequestBody EmployeePatchDto employee) {
		EmployeeResponseDto updated = employeeService.updateEmployee(id, employee);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		employeeService.deleteById(id);
		return ResponseEntity.ok("Employee with id:" + id + " deleted sucessfully...");
	}

}
