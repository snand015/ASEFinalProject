package com.community.exchange.skill.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.community.exchange.skill.DAO.Login;
import com.community.exchange.skill.DAO.Message;
import com.community.exchange.skill.DAO.PasswordResetRequestEntity;
import com.community.exchange.skill.DAO.PasswordResetResponseEntity;
import com.community.exchange.skill.DAO.Profile;
import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.DAO.User;
import com.community.exchange.skill.exception.UserNotFoundException;
import com.community.exchange.skill.repo.UserRepo;

@Service
public class UserService {
@Autowired UserRepo userRepo;
@Autowired JavaMailSender mailSender;
	public User validateUser(Login login) throws UserNotFoundException {
		User user=null;
		user=userRepo.searchUserByName(login.getUserName());
		if (user ==null) {
			
				throw new UserNotFoundException("invalid User");
			
		}else {
			if(! user.getPassword().equals(login.getPassword())){
				throw new UserNotFoundException("invalid password");
			}
		}
		
		
		return user;
		
	}
	public Profile getUserDetails(String userName,Skill skill) throws UserNotFoundException {
		User user=null;
		user=userRepo.searchUserByName(userName);
		if (user ==null) {
			
				throw new UserNotFoundException("invalid Skill Profile");
			
		}
		Profile profile= new Profile();
		profile.setAddress(user.getAddress());
		profile.setUserName(user.getUserName());
		profile.setFirstName(user.getFirstName());
		profile.setLastName(user.getLastName());
		
		profile.setSkillSet(skill);
		
		return profile;
		
	}
	public void sendMessage(Message message, String sender) {
		System.out.println("sender name is "+sender +"receiver name is "+message.getReceiverUserName());
		Message msg= new Message();
		msg.setContent(message.getContent());
		
		msg.setReceiverUserName(message.getReceiverUserName());
		msg.setSenderUserName(sender);
		msg.setTimestamp(LocalDateTime.now());
		 msg.setSkillName(message.getSkillName());
		 msg.setStatus("PEN");
		 
        userRepo.saveMessage(msg);
		
	}
	public List<Message> getMessagesForUser(String receiverUserName,String type) {
		List<Message> requests=null;
		if(type.equalsIgnoreCase("sent")) {
			System.out.println("inside sent"+receiverUserName);
			requests=userRepo.getAllRequests(receiverUserName);
			System.out.println("requests.size"+requests.size());
			
		}else {
			requests=userRepo.getMyRequests(receiverUserName);
		}
	
		return requests;
	}
	 public void createPasswordResetRequest(String email, String verificationCode, LocalDateTime expiryDateTime,String userName) {
	        PasswordResetRequestEntity request = new PasswordResetRequestEntity();
	        request.setEmail(email);
	        request.setVerificationCode(verificationCode);
	        request.setExpiryDateTime(expiryDateTime);
	        request.setUserName(userName);
	        userRepo.saveCode(request);
	    }
	 public void sendPasswordResetEmail(String toEmail, String verificationCode) {
		 SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(toEmail);
	        message.setSubject("password Reset request");
	        message.setText("Hi \n please use the code below to update your password on the community platfrom.\n the code is :"+verificationCode);

//mailSender.send(message);
	    }
	public String getUserById(String userName) throws UserNotFoundException {
	String email=	userRepo.getUserById(userName);
	if (email==null) {
		throw new UserNotFoundException("Invalid UserId");
	}return email;
		
	}
	public boolean validateOTP(String verificationCode,String userName) {
	List<PasswordResetRequestEntity> pList=null;
			pList=userRepo.validateOTP(userName);
			String localCode=null;
			if(pList!=null ) {
				localCode=pList.get(0).getVerificationCode();
			}
			if(localCode.equals(verificationCode)) {
				return true;
			}
		return false;
	}
	public void updatepassword(PasswordResetRequestEntity entity) {
		userRepo.updatePassword(entity.getUserName(),entity.getPassword());
		
	}

	
}
