package com.aurionpro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurionpro.common.RandomPassportGenerator;
import com.aurionpro.dto.EmployeePatchDto;
import com.aurionpro.dto.EmployeeRequestDto;
import com.aurionpro.dto.EmployeeResponseDto;
import com.aurionpro.entity.Employee;
import com.aurionpro.entity.Passport;
import com.aurionpro.exception.NotFoundException;
import com.aurionpro.mapper.EmployeeMapper;
import com.aurionpro.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
   
//	@Override
//	public EmployeeResponseDto addEmployee(EmployeeRequestDto request) {
//		Employee employee = employeeRepository.save(EmployeeMapper.toNewEmployee(request));
//		return EmployeeMapper.toResponse(employee);
//	}
	
	@Override
	public EmployeeResponseDto addEmployee(EmployeeRequestDto dto) {
	    Employee employee = new Employee();
	    employee.setFirstName(dto.getFirstName());
	    employee.setLastName(dto.getLastName());
	    employee.setEmail(dto.getEmail());

	    if (dto.getPassport() != null) {
	        Passport passport = new Passport();
	        passport.setIssueDate(dto.getPassport().getIssueDate());
	        passport.setExpiryDate(dto.getPassport().getExpiryDate());
	        if(dto.getPassport().getPassportNumber() != null) {
	        	passport.setPassportNumber(dto.getPassport().getPassportNumber());
	        }
	        else {
	        	passport.setPassportNumber(RandomPassportGenerator.generatePassportNumber());
	        }
	        employee.setPassport(passport);
	    }
	    return EmployeeMapper.toResponse(employeeRepository.save(employee));
	}

	
	

	@Override
	@Transactional(readOnly = true)
	public EmployeeResponseDto getEmployeeById(Integer id) {
		Employee employee = employeeRepository.findById((long)id)
				.orElseThrow(() -> new NotFoundException("Employee Not Found with id : " + id));
		return EmployeeMapper.toResponse(employee);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeResponseDto> getAllEmployees() {
	    return employeeRepository.findAll()
	    		                 .stream()
	    		                 .map(EmployeeMapper::toResponse)
	    		                 .toList();
	}

	
	@Override
	public void deleteById(Integer id) {
		if(!employeeRepository.existsById((long)id)) {
			throw new NotFoundException("Employee Not found with id : " + id);
		}
		employeeRepository.deleteById((long)id);
	}



	@Override
	public EmployeeResponseDto updateEmployee(Integer id, EmployeePatchDto patchDto) {
		Employee employee = employeeRepository.findById((long)id)
				.orElseThrow(() -> new NotFoundException("Employee Not Found with id : " + id));
		
		Passport passport = employee.getPassport();
		
		if (passport == null) {
			throw new NotFoundException("Passport not found for EmployeeID :  " + employee.getId());
		}
		
		if(patchDto.getFirstName() != null) {
			employee.setFirstName(patchDto.getFirstName());
		}
		if(patchDto.getLastName() != null) {
			employee.setLastName(patchDto.getLastName());
		}
		if(patchDto.getEmail() != null) {
			employee.setEmail(patchDto.getEmail());
		}
		
		employee.setPassport(passport);
		
		return EmployeeMapper.toResponse(employeeRepository.save(employee));
	}
	
}
