package com.aurionpro.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.aurionpro.entity.Student;
import com.aurionpro.error.StudentErrorPageResp;
import com.aurionpro.exception.StudentNotFoundException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}

	@GetMapping("/")
	public List<Student> getStudentList() {
		List<Student> list = new ArrayList<>();
		list.add(new Student(1, "Aradhya", "Dange", "dangearadhya6@gmail.com"));
		list.add(new Student(2, "Om", "Rathod", "omrathod@gmail.com"));
		return list;
	}

	@GetMapping("/{studentId}")
	public Student getStudentById(@PathVariable int studentId) {
		List<Student> list = new ArrayList<>();
		list.add(new Student(1, "Aradhya", "Dange", "dangearadhya6@gmail.com"));
		list.add(new Student(2, "Om", "Rathod", "omrathod@gmail.com"));
		list.add(new Student(3, "Sahil", "Rathod", "sahilrathod@gmail.com"));
		if (studentId >= list.size() || studentId < 0) {
			throw new StudentNotFoundException("Student Not Found with ID:" + studentId);
		}

		return list.get(studentId);
	}

	@ExceptionHandler
	public ResponseEntity<StudentErrorPageResp> handleException(StudentNotFoundException exception) {
		StudentErrorPageResp errorPageResp = new StudentErrorPageResp();
		errorPageResp.setStatus(HttpStatus.NOT_FOUND.value());
		errorPageResp.setTimestamp(System.currentTimeMillis());
		errorPageResp.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorPageResp, HttpStatus.NOT_FOUND);
	}
	
    @ExceptionHandler
    public ResponseEntity<StudentErrorPageResp> handleTypeMismatch(MethodArgumentTypeMismatchException exception) {
        StudentErrorPageResp errorPageResp = new StudentErrorPageResp();
        errorPageResp.setStatus(HttpStatus.BAD_REQUEST.value());
        errorPageResp.setTimestamp(System.currentTimeMillis());
        errorPageResp.setMessage("Invalid input: studentId must be a number.");
        return new ResponseEntity<>(errorPageResp, HttpStatus.BAD_REQUEST);
    }
}


