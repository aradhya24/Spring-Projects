package com.aurionpro;

public class KabbadiCoach  implements Coach{
	
	public  KabbadiCoach() {
		System.out.println("Constructor : " + getClass().getSimpleName());
	}
	
	@Override
	public String getDailyWorkout() {
		return "I am kabaddi coach....";
	}
}