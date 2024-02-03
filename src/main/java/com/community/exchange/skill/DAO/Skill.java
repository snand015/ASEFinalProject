package com.community.exchange.skill.DAO;

import org.springframework.stereotype.Component;

@Component
public class Skill {

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String skill;
	
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	private String description;
private String userName;

public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}	
}
