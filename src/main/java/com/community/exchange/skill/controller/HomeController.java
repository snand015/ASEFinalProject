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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.community.exchange.skill.DAO.Login;
import com.community.exchange.skill.DAO.Message;
import com.community.exchange.skill.DAO.Profile;
import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.DAO.User;
import com.community.exchange.skill.exception.ApplicationException;
import com.community.exchange.skill.exception.UserNotFoundException;
import com.community.exchange.skill.exception.UserUpdateException;
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
	public String gotoLogin( @ModelAttribute("login") Login login, HttpSession session,Model model,RedirectAttributes re ) {
		System.out.println("inside login home"+login.getUserName());
session.removeAttribute("role");
User user=null;
try {
user=	userService.validateUser(login);
}catch(UserNotFoundException e) {
	re.addFlashAttribute("errorMessage","Invalid User and Password Please try again!");
	return "redirect:/skillapp";
			
}
		
		
		
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
	
	String userName=(String)session.getAttribute("userName");
skillList=skillService.fetchUserSkills(userName);
User user=null;
try {
	user = userService.getUserDetailsById(userName);
} catch (UserNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}Profile profile= new Profile();
profile.setEmail(user.getEmail());
profile.setFirstName(user.getFirstName());
profile.setLastName(user.getLastName());
profile.setUserName(user.getUserName());
profile.setAddress(user.getAddress());
profile.setSkillSet(skillList);
model.addAttribute("profile",profile);
model.addAttribute("user",user);

	
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

    @GetMapping("update-user")
	public String updateUserDetails( @ModelAttribute("user")User user,Model model,@RequestParam("userName")String userName) {
	 User oldUser=null;
		try {
			oldUser=	userService.getUserDetailsById(userName);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("user",oldUser);
		
		return "UserUpdateScreen";
	}
	@PostMapping("update-user")
	public String updateUserConfirm( @ModelAttribute("user")User user,Model model,RedirectAttributes redirectAttributes) {
		
		try {
			userService.updateUser(user);
		} catch (UserUpdateException e) {
			
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("ErrorMessage", "User details update is failed Please check the data entered.");	
		}
		redirectAttributes.addFlashAttribute("successMessage", "User details are updated.");
	
		 return "redirect:myprofile";
	}
 
	
}