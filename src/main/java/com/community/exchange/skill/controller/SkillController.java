package com.community.exchange.skill.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.community.exchange.skill.DAO.Profile;
import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.DAO.SkillListContainer;
import com.community.exchange.skill.DAO.User;
import com.community.exchange.skill.exception.PendingDependenciesException;
import com.community.exchange.skill.exception.UserNotFoundException;
import com.community.exchange.skill.service.SkillService;
import com.community.exchange.skill.service.UserService;
import com.community.exchange.skill.exception.SkillNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/skillapp")
public class SkillController {
	@Autowired
	public UserService userService;
	@Autowired
	public SkillService skillService;
	@Autowired SkillControllerData data;
@PostMapping("/skills/add")
public String createprofile(@ModelAttribute("skill") Skill skill, @RequestParam("images") MultipartFile[] images, HttpSession session) {
	//System.out.println("inside skill controller"+skillList.toString());	
	String userName=(String)session.getAttribute("userName");
	System.out.println("user name from session"+(String)session.getAttribute("userName"));
	 if (images != null && images.length > 0) {
		   List<String> imagePaths = data.addImagesToSkill(skill, images, userName);
            skill.setImagePaths(imagePaths);
            }
	        
skillService.addSkill(skill, (String)session.getAttribute("userName"));
		return "redirect:/skillapp/myprofile";
	}

@GetMapping("/skills/update/{id}")
public String showUpdateForm(@PathVariable Long id, Model model) {
    Skill skillToUpdate = skillService.getSkillById(id);
    model.addAttribute("skillToUpdate", skillToUpdate);
    
    model.addAttribute("userName",skillToUpdate.getUserName());
    return "UpdateSkill";
}
@PostMapping("/updateSkill")
public String updateskillData(@ModelAttribute("skill")Skill skill,@RequestParam("newImages") MultipartFile[] images,@RequestParam("userName")String userName) {

	if (images != null && images.length > 0) {
		
		   List<String> imagePaths = data.addImagesToSkill(skill, images, userName);
         skill.setImagePaths(imagePaths);
         }
	        
	skillService.UpdateSkill(skill, userName);
	return "redirect:myprofile";
}
@PostMapping("/deleteImage")
@ResponseBody
public ResponseEntity<String> deleteImage(@RequestParam("imageUrl") String imageUrl) {
    // Remove the image reference from the database
    boolean deletedFromDB = skillService.deleteImageByUrl(imageUrl);


    if (deletedFromDB ) {
        return ResponseEntity.ok("Image deleted successfully.");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete image.");
    }
}
@GetMapping("/addskills")
public String loadFromToAddSkills(HttpSession session,Model model,@RequestParam("userName")String userName) {
	
	return "createSkills";
}

@GetMapping("/skills/delete/{id}")
public String deleteSkill(@PathVariable Long id) throws PendingDependenciesException{
	 Skill skillToDelete = skillService.getSkillById(id);
skillService.removeSkillById(id);  
data.deleteSkillImages(skillToDelete);
    return "redirect:/skillapp/myprofile";
}

@GetMapping("/search")
public String search(@RequestParam("skill") String search, Model model, HttpServletRequest request) {
    
    System.out.println("in search controller");
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
return "home";

}
@GetMapping("/skillDetails")
public String getSkillDetails(@RequestParam("skill") String  skill,@RequestParam("user") String  userName, Model model) throws SkillNotFoundException {

	System.out.println("user name is "+userName);
	 Skill skillSet = skillService.getSkillById(skill,userName);

	    if (skillSet == null) {
	        // Handle the case where the skill is not found
	        return "redirect:home"; // or another appropriate redirect
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
@PostMapping("/logcomplaint")
public String logcomplaint(@ModelAttribute("complaint") Skill skill,Model model) {
	
	  skillService.sendComplaintAndFlagSkill(skill);
	  model.addAttribute("complaint","Hi the complaint has been registered and action will be taken. Please reach out to admin for further grievience.");
	  return "home";
}
}