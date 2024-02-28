package com.community.exchange.skill.DAO;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;



@Component

public class User {
	@Size(min=6, message = "{username cannot be less than six characters}")
	private String UserName;
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Skill[] getSkill() {
		return skill;
	}
	public void setSkill(Skill[] skill) {
		this.skill = skill;
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
	public String getCurrentResident() {
		return currentResident;
	}
	public void setCurrentResident(String currentResident) {
		this.currentResident = currentResident;
	}

	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{6,20})", 
			message = "Password must have one upper case, one lower case,atleast one digit "
					+ "and one special character and should be between 8 and 20 characters")
	private String password;
	private Skill[] skill;
	@NotEmpty(message="first name can not be empty")
	private String firstName;
	@NotEmpty(message="first name can not be empty")
	private String lastName;
	private String currentResident;
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotEmpty(message = "Email cannot be empty")
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String role="normal";
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
