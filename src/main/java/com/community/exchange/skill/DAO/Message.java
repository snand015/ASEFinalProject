package com.community.exchange.skill.DAO;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class Message {
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSenderUserName() {
		return senderUserName;
	}
	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}
	public String getReceiverUserName() {
		return receiverUserName;
	}
	public void setReceiverUserName(String receiverUserName) {
		this.receiverUserName = receiverUserName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime date) {
		this.timestamp = date;
	}
	private Long id;

    private String senderUserName;
    private String receiverUserName;
    private String content;
    private LocalDateTime timestamp;
    private String SkillName;
	public String getSkillName() {
		return SkillName;
	}
	public void setSkillName(String skillName) {
		SkillName = skillName;
	}
private String status;
    // getters and setters
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
}