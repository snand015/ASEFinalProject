package com.community.exchange.skill.DAO;

import org.springframework.stereotype.Component;

@Component
public class PasswordResetResponseEntity {
private String to;
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
private String subject;
private String text;


}
