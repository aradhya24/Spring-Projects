package com.aurionpro.model;

import java.util.List;

public class Student {
	String firstName;
	String lastName;
	String country;
	String favoriteLanguage;
	List<String> operatingSystem;
	
	public Student(){
	}

	public String getFirstName() {
		return firstName;
	}
     
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}

	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}

	public List<String> getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(List<String> operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	
	
	
}
