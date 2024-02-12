package com.community.exchange.skill.DAO;

import java.util.List;

import javax.validation.constraints.Size;

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
private boolean irrelaventFlg;


public boolean getIrrelaventFlg() {
	return irrelaventFlg;
}
public void setIrrelaventFlg(boolean irrelaventFlg) {
	this.irrelaventFlg = irrelaventFlg;
}
@Size(max=100, message="Maximum description is upto 100 characters")
private String complaint;


public String getComplaint() {
	return complaint;
}
public void setComplaint(String complaint) {
	this.complaint = complaint;
}	
}
