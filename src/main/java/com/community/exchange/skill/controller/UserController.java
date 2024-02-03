package com.community.exchange.skill.controller;




import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.community.exchange.skill.DAO.Message;
import com.community.exchange.skill.DAO.Profile;
import com.community.exchange.skill.DAO.User;
import com.community.exchange.skill.exception.RegistractionException;
import com.community.exchange.skill.service.RegistractionService;
import com.community.exchange.skill.service.RequestService;
import com.community.exchange.skill.service.SkillService;
import com.community.exchange.skill.service.UserService;





@Controller
public class UserController {
	
	@Autowired UserService userService;
	@Autowired RegistractionService registrationService;
	@Autowired RequestService requestService;
	@PostMapping("/addUser")
	public String addUser(
		@Valid @ModelAttribute("user") User user,  BindingResult result, Model model ) throws RegistractionException {
			
			System.out.println("inside registration controller");

			        if(result.hasErrors()){
			        	System.out.println(result);
			            return "createAccount";
			        }

			        
					try {
						registrationService.addUser(user);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new RegistractionException("registration failed please contact admin for support");
					}
					model.addAttribute("dataSaved", "Registration successful");
					return "index";
				}
	
		

}



