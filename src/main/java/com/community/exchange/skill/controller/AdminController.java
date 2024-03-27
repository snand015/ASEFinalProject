package com.community.exchange.skill.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.community.exchange.skill.DAO.Login;
import com.community.exchange.skill.DAO.Message;
import com.community.exchange.skill.DAO.Profile;
import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.DAO.User;
import com.community.exchange.skill.exception.PendingDependenciesException;
import com.community.exchange.skill.exception.RegistractionException;
import com.community.exchange.skill.exception.SkillNotFoundException;
import com.community.exchange.skill.exception.UserNotFoundException;
import com.community.exchange.skill.exception.UserUpdateException;
import com.community.exchange.skill.service.RegistractionService;
import com.community.exchange.skill.service.SkillService;
import com.community.exchange.skill.service.UserService;

@Controller
@RequestMapping("/skillapp/admin")
public class AdminController {
@Autowired UserService userService;
@Autowired SkillService skillService;
@Autowired SkillControllerData data;
@Autowired RegistractionService registrationService;
	@PostMapping ("login")
	public String gotoLogin( @ModelAttribute("login") Login login, HttpSession session,Model model ) throws  UserNotFoundException{
		System.out.println("inside login home"+login.getUserName());
	
		User user=userService.validateUser(login);
		
		System.out.println("landing on home");
		session.setAttribute("userName",login.getUserName() );
		session.setAttribute("role", "admin");
		model.addAttribute("userName",login.getUserName());
        	return "AdminHome";
        

		
	}
	@GetMapping("fetch-flagged")
	public String fetchFlaggedPosts(Model model) {
	List<Skill> flaggedPosts=null;
		try {
			flaggedPosts=	skillService.getFlaggedPosts();
		} catch (SkillNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("flaggedSkills",flaggedPosts);
		return "posts";
		
	}
	@PostMapping("removePost")
	public String deletePost(@ModelAttribute ("message") Message message, Model model,HttpSession session) {
		System.out.println("inside send message controller"+message.getReceiverUserName());
		message.setStatus("COM");
		userService.sendMessage(message,(String)session.getAttribute("userName"));
		try {
			skillService.removeSkillById(message.getSkillId());
		} catch (PendingDependenciesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("errorMessage", "Unable to delete the post pending requests on this Post.");
		}
		model.addAttribute("dataSaved", "Post is removed and notified to the owner.");
		return "AdminHome";
	}
	@GetMapping("viewPost")
	public String viewSkillPost(@RequestParam ("skill")long id,Model model) {
		Skill skillSet=skillService.getSkillById(id);
		 if (skillSet == null) {
		        // Handle the case where the skill is not found
		        return "redirect:AdminHome"; // or another appropriate redirect
		    }else {
		    	Profile profile=null;
		    	try {
		    		profile=	userService.getUserDetails(skillSet.getUserName(),skillSet);
		    	} catch (UserNotFoundException e) {
		    		// TODO Auto-generated catch block
		    		e.printStackTrace();
		    	}
		    	
		    	
		        model.addAttribute("profile", profile);
		        System.out.println("loading skill details to request ");
		        return "skillDetails"; 
	}
	}
	@PostMapping("addUser")
	public String addUser(@Valid @ModelAttribute("user") User user,  BindingResult result, ModelMap model,RedirectAttributes redirectAttributes)  {
		  if(result.hasErrors()){
	        	System.out.println(result);
	        	 return "AdminHome";
	        }

	        
			try {
				registrationService.addUser(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage",	"registration failed please check the details entered"+e.getMessage());
			 return "redirect:/skillapp/admin/AdminHome?tab=addUser";
			 }
			model.addAttribute("dataSaved", "Registration successful");
			return "AdminHome";
	}
	@PostMapping("deleteUser")
	public String removeUser(@RequestParam("userName")String userName, Model model) {
		Boolean success=userService.removeUser(userName);
		String message=" User Removed sucessfully";
		if(!success) {
			message="unable to remove user check with support Team!";
		}
		model.addAttribute("dataSaved", message);
		return "AdminHome";
	}
	@GetMapping("getUser")
	public String findUser(@ModelAttribute("user") User user, Model model) {
	List<User> profiles=	userService.getUserDetails(user);
	model.addAttribute("userList",profiles);
	return "userList";
	}
	@GetMapping("UserDetails")
	public String viewUserdetails(@RequestParam("userName") String  userName , Model model) {
	User user=null;
	try {
		user = userService.getUserDetailsById(userName);
	} catch (UserNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		model.addAttribute("errorMessage", "user Details are not valid. please try again");
		return "userList";
	}
	List<Skill> skills=	skillService.fetchUserSkills(user.getUserName());
	Profile profile= new Profile();
	profile.setEmail(user.getEmail());
	profile.setFirstName(user.getFirstName());
	profile.setLastName(user.getLastName());
	profile.setUserName(user.getUserName());
	profile.setAddress(user.getAddress());
	profile.setSkillSet(skills);
	model.addAttribute("profile",profile);
	model.addAttribute("user",user);
	return "profile";
	}
	@GetMapping("/AdminHome")
	public String adminHome(Model model) {
	   
	    return "AdminHome"; 
	}
	
	@GetMapping("update-user")
	public String updateUserDetails(@RequestParam("userName")String userName, @ModelAttribute("user")User user,Model model) {
		System.out.println("user name in update user is "+userName);
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
		String userName=user.getUserName();
		try {
			userService.updateUser(user);
		} catch (UserUpdateException e) {
			
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("ErrorMessage", "User details update is failed Please check the data entered.");	
		}
		redirectAttributes.addFlashAttribute("successMessage", "User details are updated.");
	
		return "redirect:/skillapp/admin/UserDetails?userName="+userName+"";
	}
	
	@GetMapping("/addskills")
	public String loadFromToAddSkills(HttpSession session,Model model,@RequestParam("userName")String userName) {
		model.addAttribute("userName", userName);
		return "createSkills";
	}

	@GetMapping("/skills/delete/{id}")
	public String deleteSkill(@PathVariable Long id) throws PendingDependenciesException{
		 Skill skillToDelete = skillService.getSkillById(id);
		 String userName=skillToDelete.getUserName();
	skillService.removeSkillById(id);  
	//deleteSkillImages(skillToDelete);
	return "redirect:/skillapp/admin/UserDetails?userName="+userName+"";
	}
	@PostMapping("/skills/add")
	public String createprofile(@ModelAttribute("skill") Skill skill, @RequestParam("images") MultipartFile[] images, HttpSession session) {
		//System.out.println("inside skill controller"+skillList.toString());	
		String userName=skill.getUserName();
		System.out.println("username while adding skill from admin"+userName);
		//System.out.println("user name from session"+(String)session.getAttribute("userName"));
		 if (images != null && images.length > 0) {
			   List<String> imagePaths = data.addImagesToSkill(skill, images, userName);
	            skill.setImagePaths(imagePaths);
	            }
		        
		skillService.addSkill(skill, userName);
		return "redirect:/skillapp/admin/UserDetails?userName="+userName+"";
		}
	 @GetMapping("logout")
	    public String logout(HttpSession session){
	        System.out.println("Ending user session");
	        session.removeAttribute("userName" );
	        session.removeAttribute("role" );
	        session.invalidate();
	        //System.out.println(session.getAttribute("login"));
	      

	        return "redirect:/skillapp";

	    }
	 
	 
	 @GetMapping("/search")
	 public String search(@RequestParam("skill") String search, Model model, HttpServletRequest request) {
	     
	     System.out.println("in search admin");
	     System.out.println("search criteria: "+search);
	     List<Skill> resultSkills=null;
	     try {
	    resultSkills = skillService.findByNameContainingIgnoreCase(search);
	     }catch( Exception e) {
	     	e.printStackTrace();
	     	model.addAttribute("searchfailed","Sorry! No skill related to the search criteria is available. Thank you for using the platfrom try new skill.");
	     }
	     if(resultSkills!=null) {
	     System.out.println(resultSkills.size());
	     }
	     model.addAttribute("resultSkills", resultSkills);
	 return "AdminHome";

	 }
}
