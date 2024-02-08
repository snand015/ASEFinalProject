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
	public void addSkill(Skill profile,String userName) {
		System.out.println("user name "+userName);
		
		skillRepo.addSkill( profile, userName);
			
		
	}
	public Skill getSkillById(String skill, String userName) throws skillNotFoundException {
		Skill skillSet=skillRepo.findskillSet(skill,userName);
		if(skillSet==null) {
			throw new skillNotFoundException("No Talents related are found, Sorry for the inconvineance");
		}
		return skillSet;
	}
	public Skill getSkillById(Long id) {
		Skill skill=null;
		List<Skill> skillList=skillRepo.getSkillById(id);
		if(!skillList.isEmpty()) {
			skill=skillList.get(0); 
		}
		return skill;
	}
	public void removeSkillById(Long id) {
		skillRepo.deleteSkillById(id);
		
	}
	public void UpdateSkill(Skill skill, String userName) {
		Skill oldSkill=skill;
	
			skillRepo.modifySkillDetails(skill,userName);
		
		
	}
	public List<Skill> fetchUserSkills(String userName){
		return skillRepo.getskillsByUserName(userName);
	}
	public boolean deleteImageByUrl(String imageUrl) {
		// TODO Auto-generated method stub
		return false;
	}
}
