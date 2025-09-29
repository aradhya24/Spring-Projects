package com.aurionpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;


public class HelloWorldController {
	
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
//	@RequestMapping("/processForm")
//	public String processForm(HttpServletRequest request, Model model) {
//		
//		String name = "Yo!   " + request.getParameter("name").toUpperCase();
//		
//		model.addAttribute("message",name);
//		
//		return "helloworld";
//	}
	
	@RequestMapping("/processForm")
	public String processForm(@RequestParam("name") String name, Model model) {
		
		name = "Yo!   " + name.toUpperCase();
		
		model.addAttribute("message",name);
		
		return "helloworld";
	}
	
//	@RequestMapping("/processForm")
//	public String processForm() {
//		return "helloworld";
//	}	

}
