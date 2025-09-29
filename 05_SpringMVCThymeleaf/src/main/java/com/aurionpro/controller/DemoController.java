package com.aurionpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


public class DemoController {

	@GetMapping("/")
	public String sayHello(Model model) {
		model.addAttribute("theDate",java.time.LocalDateTime.now());
		return "helloworld";
	}

}
