package com.community.exchange.skill.controller;




import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.community.exchange.skill.DAO.Message;
import com.community.exchange.skill.DAO.PasswordResetRequestEntity;
import com.community.exchange.skill.DAO.Profile;
import com.community.exchange.skill.DAO.User;
import com.community.exchange.skill.exception.RegistractionException;
import com.community.exchange.skill.exception.UserNotFoundException;
import com.community.exchange.skill.exception.UserUpdateException;
import com.community.exchange.skill.service.RegistractionService;
import com.community.exchange.skill.service.RequestService;
import com.community.exchange.skill.service.SkillService;
import com.community.exchange.skill.service.UserService;





@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired UserService userService;
	@Autowired RegistractionService registrationService;
	@Autowired RequestService requestService;
	
	@GetMapping ("register")
	public String gotoRegistration() {
		System.out.println("inside registration of home");
		return "createAccount";
	}
	
	
	@PostMapping("addUser")
	public String addUser(
		@Valid @ModelAttribute("user") User user,  BindingResult result, ModelMap model ) throws RegistractionException {
			
			System.out.println("inside registration controller");

			        if(result.hasErrors()){
			        	System.out.println(result);
			            return  "createAccount";
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
	@GetMapping("forgot-password")
	public String forgotPassword(Model model) {
		
		return "forgotPassword";
	}
	 @PostMapping("send-otp-and-reset")
	    public String forgotPassword(@RequestParam("userName") String userName,Model model) {
	        // Generate verification code
		 System.out.println("userName in opt method"+userName);
	        String verificationCode = generateVerificationCode();
	        // Set expiry time
	        LocalDateTime expiryDateTime = LocalDateTime.now().plusHours(24);
	        // Save password reset request
	 String email = null;
	try {
		email = userService.getUserById(userName);
	} catch (UserNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}if(email!=null) {
		 userService.createPasswordResetRequest(email, verificationCode, expiryDateTime,userName);
	        // Send email with verification code
	        userService.sendPasswordResetEmail(email, verificationCode);
	        model.addAttribute("userName",userName);
	        return "passwordReset";
	}else {
		model.addAttribute("failure","Invalid userId please check with adminstrator.");
	return "forgotPassword";
	}


	    }
	 
	 
	 @PostMapping("validate-otp-and-reset")
	 public String validateAndResetPassword(@ModelAttribute ("pass") PasswordResetRequestEntity entity,
			 @RequestParam("userName") String userName,ModelMap model) {
boolean flag=	false;
System.out.println("inside validate otp controller "+userName);
flag=userService.validateOTP(entity.getVerificationCode(),userName);
	if (flag==true) {
		entity.setUserName(userName);
		userService.updatepassword(entity);
	}else {
		model.addAttribute("failure","Invalid Verifcation code please try again.");
		return "forgotpassword";
	}
	model.addAttribute("passwordSuccess", "Password Updated Successfully, please try to login");
		 return "index";
		 
	 }
	 
	 public  String generateVerificationCode() {
	        // Create a Random object
	        Random random = new Random();
	        
	        // Generate a random integer between 100000 and 999999 (inclusive)
	        int min = 100000;
	        int max = 999999;
	        int verificationCode = random.nextInt(max - min + 1) + min;
	        String code=String.valueOf(verificationCode);
	        // Return the generated verification code
	        return code;
	    }	

	
}



