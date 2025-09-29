package com.aurionpro.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aurionpro.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class EmployeeDaoImpl implements EmployeeDao {

	private EntityManager entityManager;

	@Autowired
	public EmployeeDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return entityManager.find(Employee.class, id);
	}

	@Override
	public List<Employee> getAllEmployee() {
		TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void updateEmployeeName(Integer id, String name) {
		Employee employee = entityManager.find(Employee.class, name);
		employee.setEmpName(name);
		entityManager.merge(employee);
	}

	@Override
	@Transactional
	public void deleteEmployeeById(Integer id) {
		Employee employee = entityManager.find(Employee.class, id);
		entityManager.remove(employee);
	}

	@Override
	public void createEmployee(Employee employee) {
		entityManager.persist(employee);
	}

}
