package com.community.exchange.skill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.DAO.User;
import com.community.exchange.skill.exception.UserNotFoundException;
import com.community.exchange.skill.exception.skillNotFoundException;
import com.community.exchange.skill.repo.SkillRepository;
import com.community.exchange.skill.repo.UserRepo;

@Service
public class SkillService {
	@Autowired SkillRepository skillRepo;
	@Autowired UserRepo userrepo;

	public List<Skill> findByNameContainingIgnoreCase(String search) throws skillNotFoundException {
		System.out.println("calling repository");
		List<Skill> searchList=skillRepo.findByNameContainingIgnoreCase(search);
		if(searchList==null || searchList.isEmpty()) {
			throw new skillNotFoundException("No Talents related are found, Sorry for the inconvineance");
		}
		return searchList;
	
	}
	public void UpdateSkill(List<Skill> profile,String userName) {
		skillRepo.addSkill( profile, userName);
			
		
	}
	public Skill getSkillById(String skill, String userName) throws skillNotFoundException {
		Skill skillSet=skillRepo.findskillSet(skill,userName);
		if(skillSet==null) {
			throw new skillNotFoundException("No Talents related are found, Sorry for the inconvineance");
		}
		return skillSet;
	}
}
/*
 * else { for (Skill skill : searchList) { User user=null; try { user=
 * userrepo.searchUserByName(skill.getUserName()); } catch
 * (UserNotFoundException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } if (user!=null) {
 * 
 * } }
 * 
 * }
 */