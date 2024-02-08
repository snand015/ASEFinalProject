package com.community.exchange.skill.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.community.exchange.skill.DAO.Login;
import com.community.exchange.skill.DAO.Message;
import com.community.exchange.skill.DAO.PasswordResetRequestEntity;
import com.community.exchange.skill.DAO.Profile;
import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.DAO.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class DefaultModelAttributeController {

    @ModelAttribute("user")
    public User getDefaultUser(){
        return new User();
    }

	/*
	 * @ModelAttribute("genderItems") public List<String> getGenderItems(){ return
	 * Arrays.asList(new String[]{"Male", "Female", "Other"}); }
	 */

	
	  @ModelAttribute("login")
	  public Login getDefaultLogin()
	  {
		  return new Login();
	  }
	  @ModelAttribute("profile")
	  public  Skill getDefaultSkill()
	  {
		  return new  Skill();
	  }
	  @ModelAttribute("resultSkills")
	  public  List<Skill> getDefaultSkills()
	  {
		  return null;
	  }
	  @ModelAttribute("profile")
	  public  Profile getDefaultProfile()
	  {
		  Skill sk= new Skill();
		  Profile p= new Profile();
		  
		  p.setSkillSet(sk);
		  return p;
	  }
	  @ModelAttribute("skill")
	  public Skill getdefaultSkillList(){
		  return new Skill();
	  }
	  @ModelAttribute("message")
	  public  Message getDefaultmessage()
	  {
		  return new Message();
	  }
	  @ModelAttribute("response")
	  public  Message getDefaultResponse()
	  {
		  return new Message();
	  }
	  @ModelAttribute("pass")
	  public  PasswordResetRequestEntity getdefaultpasswordchange()
	  {
		  return new PasswordResetRequestEntity();
	  }
}
