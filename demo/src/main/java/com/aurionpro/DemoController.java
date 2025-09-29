package com.aurionpro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	private Coach coach;
	private Coach coach2;
	
	@Autowired
	public DemoController(@Qualifier("kabaddiCoach" ) Coach coach,
			              @Qualifier("footBallCoach") Coach coach2) {
	    this.coach = coach;
	    this.coach2 = coach2;
	}

	
//	@Autowired
//	public void setCoach(Coach coach) {
//		this.coach = coach;
//	}
	
	@GetMapping("/dailyWorkout")
	public String getWorkout() {
		return coach.getDailyWorkout();
	}
	
	//for checking singleton
//	@GetMapping("/dailyWorkout")
//	public boolean getWorkout() {
//		return coach.equals(coach2);
//	}

}
