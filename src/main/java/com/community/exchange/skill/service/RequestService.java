package com.community.exchange.skill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.exchange.skill.DAO.Message;
import com.community.exchange.skill.repo.UserRepo;

@Service
public class RequestService {
	@Autowired UserRepo userRepo;

	public Message getTalentRequestById(Long id) {
	return	userRepo.getRequestById(id);
		
	}

	public void markRequestAsResponded(Long id, String status) {
		
		
		userRepo.updatemessageStatus(id,status);
		
	}

	public void deleteTalentRequest(Long id) {
		//marks as completed and dont display in the request list
		String status="DEL";
		userRepo.updatemessageStatus(id,status);	
	}

}
