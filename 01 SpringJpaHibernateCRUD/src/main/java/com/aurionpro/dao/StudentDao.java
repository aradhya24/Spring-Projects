package com.aurionpro.dao;

import java.util.List;

import com.aurionpro.entity.Student;


public interface StudentDao {
	
	public void createStudent(Student student);
	
	public int saveMultipleStudent(String lastName);
	
	public Student findById(Integer id);
	
	public List<Student> findByFirstName(String firstName);
	
	public void deleteById(Integer id);
	
	public int deleteAllStudent();
	
	public List<Student> findAll();
	
	public void updateStudent(Integer id, String name);

}
