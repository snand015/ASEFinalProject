package com.community.exchange.skill.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.community.exchange.skill.DAO.Login;
import com.community.exchange.skill.DAO.Message;
import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.DAO.User;
import com.community.exchange.skill.exception.ApplicationException;
import com.community.exchange.skill.exception.UserNotFoundException;
import com.community.exchange.skill.service.SkillService;
import com.community.exchange.skill.service.UserService;



@Controller
@SessionAttributes("login")
@RequestMapping("/skillapp")
public  class HomeController{
	@Autowired UserService userService;
	@Autowired SkillService skillService;
	
	
	
	@GetMapping("")
	public String gotoHome() {
		System.out.println("inside controller");
		return "index";
	}
	
	
	@PostMapping ("login")
	public String gotoLogin( @ModelAttribute("login") Login login, HttpSession session,Model model ) throws  UserNotFoundException{
		System.out.println("inside login home"+login.getUserName());
	
		User user=userService.validateUser(login);
		
		System.out.println("landing on home");
		session.setAttribute("userName",login.getUserName() );
		model.addAttribute("userName",login.getUserName());
        	return "home";
        

		
	}
	@GetMapping ("home")
	public String gotoHome(  HttpSession session,Model model ) throws  UserNotFoundException{
		
		
		System.out.println("landing on home");
		session.setAttribute("userName",(String)session.getAttribute("userName") );
		model.addAttribute("userName",(String)session.getAttribute("userName"));
        	return "home";
        

		
	}

@GetMapping("myprofile")
public String createSkillProfile(Model model,HttpSession session) {
	List<Skill>skillList=new ArrayList();
	
	
skillList=skillService.fetchUserSkills((String)session.getAttribute("userName"));
	model.addAttribute("skills",skillList);
	return "profile";
	
}
    @GetMapping("logout")
    public String logout(HttpSession session){
        System.out.println("Ending user session");
        session.removeAttribute("userName" );
        
        session.invalidate();
        //System.out.println(session.getAttribute("login"));
      

        return "redirect:/skillapp";

    }

	
	
}