package com.aurionpro;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aurionpro.dao.StudentDao;
import com.aurionpro.entity.Student;

@SpringBootApplication
public class SpringJpaHibernateCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaHibernateCrudApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			create(studentDao);
//			readById(studentDao);
//			deleteById(studentDao);
//			deleteAllStudent(studentDao);
//			updateStudentName(studentDao);
//			queryForStudents(studentDao);
//			updateAllStudentLastName(studentDao);
//			getStudentByFirstName(studentDao);
		};
	}

	private void create(StudentDao studentDao) {
		System.out.println("Creating new student");
		Student student = new Student("Sahil", "Dange", "dangearadhya6@gmail.com");

		System.out.println("Saving Student...");
		studentDao.createStudent(student);

		System.out.println("Student saved in database id : " + student.getId());

	}

	private void readById(StudentDao studentDao) {

		int id = 1;
		System.out.println("Searching with id: " + id);

		Student foundStudent = studentDao.findById(id);
		System.out.println("Fountd student : " + foundStudent);
	}

	private void deleteById(StudentDao studentDao) {

		int id = 1;

		studentDao.deleteById(id);

		System.out.println("Student deleted successfully with id: " + id);

	}

	private void deleteAllStudent(StudentDao studentDao) {

		int rows = studentDao.deleteAllStudent();

		System.out.println("Student deleted successfully with id: " + rows);

	}

	private void queryForStudents(StudentDao studentDao) {
		List<Student> students = studentDao.findAll();

		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void getStudentByFirstName(StudentDao studentDao) {
		List<Student> students = studentDao.findByFirstName("Himesh");

		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void updateStudentName(StudentDao studentDao) {
		studentDao.updateStudent(2, "Danny");
		System.out.println("Student name updated sucessfully... Id : " + 2);
	}

	private void updateAllStudentLastName(StudentDao studentDao) {
		int rows = studentDao.saveMultipleStudent("Developer");
		System.out.println("Sucessfully update last name of " + rows + " rows....");
	}

}
