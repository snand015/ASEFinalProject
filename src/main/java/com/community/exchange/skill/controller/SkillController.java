package com.community.exchange.skill.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.community.exchange.skill.DAO.Profile;
import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.DAO.SkillListContainer;
import com.community.exchange.skill.DAO.User;
import com.community.exchange.skill.exception.UserNotFoundException;
import com.community.exchange.skill.exception.skillNotFoundException;
import com.community.exchange.skill.service.SkillService;
import com.community.exchange.skill.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class SkillController {
@Autowired UserService userService;

@Autowired SkillService skillService;
@PostMapping("/skills/add")
public String createprofile(@ModelAttribute("skill") Skill skill, HttpSession session) {
	//System.out.println("inside skill controller"+skillList.toString());	
	
	System.out.println("user name from session"+(String)session.getAttribute("userName"));
	skillService.addSkill(skill, (String)session.getAttribute("userName"));
		return "redirect:/myprofile";
	}
@GetMapping("/skills/update/{id}")
public String showUpdateForm(@PathVariable Long id, Model model) {
    Skill skillToUpdate = skillService.getSkillById(id);
    model.addAttribute("skillToUpdate", skillToUpdate);
    return "UpdateSkill";
}
@PostMapping("/updateSkill")
public String updateskillData(@ModelAttribute("skill")Skill skill,HttpSession session) {
	skillService.UpdateSkill(skill, (String)session.getAttribute("userName"));
	return "redirect:/myprofile";
}
@GetMapping("/addskills")
public String loadFromToAddSkills(HttpSession session,Model model) {
	
	return "createSkills";
}

@GetMapping("/skills/delete/{id}")
public String deleteSkill(@PathVariable Long id) {
skillService.removeSkillById(id);   
    return "redirect:/myprofile";
}

@GetMapping("/search")
public String search(@RequestParam("skill") String search, Model model, HttpServletRequest request) throws skillNotFoundException{
    
    System.out.println("in search controller");
    System.out.println("search criteria: "+search);
    List<Skill> resultSkills = skillService.findByNameContainingIgnoreCase(search);
    System.out.println(resultSkills.size());
    model.addAttribute("resultSkills", resultSkills);
return "home";

}
@GetMapping("/skillDetails")
public String getSkillDetails(@RequestParam("skill") String  skill,@RequestParam("user") String  userName, Model model) throws skillNotFoundException {

	System.out.println("user name is "+userName);
	 Skill skillSet = skillService.getSkillById(skill,userName);

	    if (skillSet == null) {
	        // Handle the case where the skill is not found
	        return "redirect:/home"; // or another appropriate redirect
	    }else {
	    	Profile profile=null;
	    	try {
	    		profile=	userService.getUserDetails(userName,skillSet);
	    	} catch (UserNotFoundException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	
	    	
	        model.addAttribute("profile", profile);
	        System.out.println("loading skill details to request ");
	        return "skillDetails"; 
	    }
	// skillDetails.jsp
}
}