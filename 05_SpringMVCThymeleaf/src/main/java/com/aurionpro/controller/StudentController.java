package com.aurionpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurionpro.model.Student;

@Controller
public class StudentController {
     
	
	@RequestMapping("/showForm")
	public String showForm(Model model) {
		Student student = new Student();
		
		model.addAttribute("student", student);
		model.addAttribute("countries", countries);
		model.addAttribute("languages",languages);
		
		return "student-form";
	}
	
	
	@RequestMapping("/processForm")
	public String processForm(@ModelAttribute("student") Student student, Model model) {
		
		return "student-confirmation";
	}
	
	
	@Value("${countries}")
	private List<String> countries;
	
	@Value("${languages}")
	private List<String> languages;
}
