package com.test;

import org.springframework.stereotype.Component;

import com.aurionpro.Coach;

@Component
public class FootBallCoach implements Coach{
	
	public FootBallCoach() {
		System.out.println("Constructor : " + getClass().getSimpleName());
	}
	
	@Override
	public String getDailyWorkout() {
		return "I am Football coach....";
	}
}
