package com.aurionpro;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class HockeyCoach implements Coach{
	
	public HockeyCoach() {
		System.out.println("Constructor : " + getClass().getSimpleName());
	}

	@Override
	public String getDailyWorkout() {
		return "I am hockey coach....";
	}
     
}
