package com.community.exchange.skill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.exchange.skill.DAO.User;
import com.community.exchange.skill.repo.UserRepo;

@Service
public class RegistractionService {
@Autowired UserRepo userRepo;	
public void addUser(User user) {
	userRepo.addUser(user);
}
}
