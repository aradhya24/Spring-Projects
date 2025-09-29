package com.aurionpro.dao;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
    
	private EntityManager entityManager;
	
	
	@Autowired
	public EmployeeDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


    @Override
	public List<Employee> getAllEmployees() {
		TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
		
		List<Employee> result = query.getResultList();
		return result;
	}


	@Override
	public Employee getEmployeeById(Integer id) {
		return entityManager.find(Employee.class, id);
	}


	@Override
	@Transactional
	public Employee save(Employee employee) {
		return entityManager.merge(employee);
	}


	@Override
	@Transactional
	public String deleteEmployee(Integer id) {
		Employee employee = entityManager.find(Employee.class, id);
		entityManager.remove(employee);
		return "Employee id:" + id + " deleted sucessfully";
	}
	
	
	

}
