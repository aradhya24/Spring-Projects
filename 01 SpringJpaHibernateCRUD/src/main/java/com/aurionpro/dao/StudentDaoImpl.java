package com.aurionpro.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository
public class StudentDaoImpl implements StudentDao{
	
	private EntityManager entityManager;
	
	@Autowired
	public StudentDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	@Override
	@Transactional
	public void createStudent(Student student) {
		entityManager.persist(student);
	}


    @Transactional
	@Override
	public int saveMultipleStudent(String lastName) {
		int numRowsUpdated = entityManager.createQuery(
                                   "UPDATE Student s SET s.lastName = :lastName")
				                   .setParameter("lastName", lastName)
				                   .executeUpdate();
		return numRowsUpdated;
	}



	@Override
	public Student findById(Integer id) {
		return entityManager.find(Student.class, id);
	}



	@Override
	@Transactional
	public void deleteById(Integer id) {
		 Student student = entityManager.find(Student.class, id);
		 entityManager.remove(student);
	}
	
	



	@Override
	public List<Student> findAll() {
		TypedQuery<Student> query = entityManager.createQuery("FROM Student" , Student.class);
		return query.getResultList();
	}


    @Transactional
	@Override
	public void updateStudent(Integer id, String name) {
		 Student student = entityManager.find(Student.class, id);
		 student.setFirstName(name);
		 //entityManager.merge(student);
	}



	@Override
	public List<Student> findByFirstName(String firstName) {
		TypedQuery<Student> query = entityManager.createQuery(
                                         "FROM Student WHERE firstName = :firstName", Student.class)
				                         .setParameter("firstName", firstName);
		return query.getResultList();
	}



	@Override
	@Transactional
	public int deleteAllStudent() {
	    return entityManager.createQuery("DELETE FROM Student")
	                        .executeUpdate();
	}

	
    
    
	
	
	

}
