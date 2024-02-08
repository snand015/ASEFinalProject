package com.community.exchange.skill.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Skill {
	private Long Id;

	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
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
private List<String> imagePaths;


public List<String> getImagePaths() {
    return imagePaths;
}

public void setImagePaths(List<String> imagePaths) {
    this.imagePaths = imagePaths;
}
	
}
