package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dto.EmployeePatchDto;
import com.aurionpro.dto.EmployeeRequestDto;
import com.aurionpro.dto.EmployeeResponseDto;

public interface EmployeeService {
	
	EmployeeResponseDto addEmployee(EmployeeRequestDto request);
	
	EmployeeResponseDto getEmployeeById(Integer id);
	
	List<EmployeeResponseDto> getAllEmployees();
	
	void deleteById(Integer id);
	
	EmployeeResponseDto updateEmployee(Integer id, EmployeePatchDto request);
	
     
}
