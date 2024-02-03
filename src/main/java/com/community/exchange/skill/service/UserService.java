package com.community.exchange.skill.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.exchange.skill.DAO.Login;
import com.community.exchange.skill.DAO.Message;
import com.community.exchange.skill.DAO.Profile;
import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.DAO.User;
import com.community.exchange.skill.exception.UserNotFoundException;
import com.community.exchange.skill.repo.UserRepo;

@Service
public class UserService {
@Autowired UserRepo userRepo;
	public User validateUser(Login login) throws UserNotFoundException {
		User user=null;
		user=userRepo.searchUserByName(login.getUserName());
		if (user ==null) {
			
				throw new UserNotFoundException("invalid password");
			
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
			System.out.println("inside sent");
			requests=userRepo.getAllRequests(receiverUserName);
			System.out.println("requests.size"+requests.size());
			
		}else {
			requests=userRepo.getMyRequests(receiverUserName);
		}
	
		return requests;
	}

	
}
