package com.aurionpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurionpro.model.Student;

import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;

@Controller
public class StudentController {
     
	
	@RequestMapping("/showForm")
	public String showForm(Model model) {
		Student student = new Student();
		
		model.addAttribute("student", student);
		
		return "student-form";
	}
	
	
	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("student") Student student,
            BindingResult bindingResult,
            Model model) {
		
		if(bindingResult.hasErrors()) {
			return "student-form";
		}
		return "student-confirmation";
	}
	
	
	//controller called it before any method in controller to be called 
	//remove leading and trailing whitespace 
	//and has only white space then it will trim it to null
	@InitBinder
	public void initBinder(WebDataBinder data) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		data.registerCustomEditor(String.class, editor);
	}

}

//cascade type persist,merge,delete

// CASCADE DELETE 

// it means suppose instructor delete then course also deletes , and vice versa 
//this apply to all cascade operations

// Fetch type lazy and eager laoding
// by default eager loading (spring) //not recommended

// eager means when student load it will also load tables mapped to it

// lazy means : on demand loading
