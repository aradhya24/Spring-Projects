package com.aurionpro.mapper;

import com.aurionpro.dto.EmployeePatchDto;
import com.aurionpro.dto.EmployeeRequestDto;
import com.aurionpro.dto.EmployeeResponseDto;
import com.aurionpro.dto.PassportRequestDto;
import com.aurionpro.dto.PassportResponseDto;
import com.aurionpro.entity.Employee;
import com.aurionpro.entity.Passport;

public final class EmployeeMapper {
	
	private EmployeeMapper(){
	}
	
	
	//Create -> Entity
	
	public static Employee toNewEmployee(EmployeeRequestDto employeeRequestDto) {
		Employee employee = new Employee();
		employee.setFirstName(employeeRequestDto.getFirstName());
		employee.setLastName(employeeRequestDto.getLastName());
		employee.setEmail(employeeRequestDto.getEmail());
		employee.setPassport(toNewPassport(employeeRequestDto.getPassport()));
		
		return employee;
	}
	
	public static Passport toNewPassport(PassportRequestDto passportRequestDto) {
		Passport passport = new Passport();
		passport.setPassportNumber(passportRequestDto.getPassportNumber());
		passport.setIssueDate(passportRequestDto.getIssueDate());
		passport.setExpiryDate(passportRequestDto.getExpiryDate());
		
		return passport;
	}
	
	
	
	
	//Entity -> Response
	
	
	public static EmployeeResponseDto toResponse(Employee employee) {
		EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
		employeeResponseDto.setId(employee.getId());
		employeeResponseDto.setFirstName(employee.getFirstName());
		employeeResponseDto.setLastName(employee.getLastName());
		employeeResponseDto.setEmail(employee.getEmail());
		employeeResponseDto.setPassport(toDto(employee.getPassport()));
		
		return employeeResponseDto;
	}
	
	
	public static PassportResponseDto toDto(Passport passport) {
		PassportResponseDto passportResponseDto = new PassportResponseDto();
		passportResponseDto.setPassportNumber(passport.getPassportNumber());
		passportResponseDto.setExpiryDate(passport.getExpiryDate());
		passportResponseDto.setIssueDate(passport.getIssueDate());
		
		return passportResponseDto;
	}
	
	
	
	
	
	//update 
	
	public static Employee toUpdateEmployee(EmployeePatchDto employeePatchDto) {
		Employee employee = new Employee();
		employee.setFirstName(employeePatchDto.getFirstName());
		employee.setLastName(employeePatchDto.getLastName());
		employee.setEmail(employeePatchDto.getEmail());
		
		return employee;
	}
     
}
