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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
public String createprofile(@ModelAttribute("skill") Skill skill, @RequestParam("images") MultipartFile[] images, HttpSession session) {
	//System.out.println("inside skill controller"+skillList.toString());	
	String userName=(String)session.getAttribute("userName");
	System.out.println("user name from session"+(String)session.getAttribute("userName"));
	 if (images != null && images.length > 0) {
		   List<String> imagePaths = addImagesToSkill(skill, images, userName);
            skill.setImagePaths(imagePaths);
            }
	        
	skillService.addSkill(skill, (String)session.getAttribute("userName"));
		return "redirect:/myprofile";
	}
private List<String> addImagesToSkill(Skill skill, MultipartFile[] images, String userName) {
	List<String> imagePaths=new ArrayList();
	    // Loop through each uploaded image file
	    for (MultipartFile image : images) {
	        // Check if the image file is not empty
	        if (!image.isEmpty()) {
	            try {
	                // Get the bytes of the image file
	            	  String fileName =  image.getOriginalFilename();
	                  
	                  // Define the directory path to save the image
	                  String directoryPath = "src/main/resources/static/images/" + userName + "/" + skill.getSkill();
	                  
	                  // Create the directory if it doesn't exist
	                  File directory = new File(directoryPath);
	                  if (!directory.exists()) {
	                      directory.mkdirs();
	                  }
	                 
	                  // Construct the path where the image will be saved
	                  Path filePath = Paths.get(directoryPath, fileName);
	                  
	                  // Save the image bytes to the file
	                  Files.write(filePath, image.getBytes());
	                  
	                  // Set the image path in the skill object
	                  String imagePath = "images/" + userName + "/" + skill.getSkill() + "/" + fileName;
	                  // Assuming you have a setter method for the image path in the Skill class
	                  if(skill.getImagePaths()==null||!(skill.getImagePaths().contains(imagePath))) {
	                	  imagePaths.add(imagePath);  
	                  }
	                 

	                
	                
	                
	            } catch (IOException e) {
	                // Handle the exception appropriately
	                e.printStackTrace();
	            }
	        }
	        }
	return imagePaths;
}
@GetMapping("/skills/update/{id}")
public String showUpdateForm(@PathVariable Long id, Model model) {
    Skill skillToUpdate = skillService.getSkillById(id);
    model.addAttribute("skillToUpdate", skillToUpdate);
    return "UpdateSkill";
}
@PostMapping("/updateSkill")
public String updateskillData(@ModelAttribute("skill")Skill skill,@RequestParam("newImages") MultipartFile[] images,HttpSession session) {
	String userName=(String) session.getAttribute("userName");
	if (images != null && images.length > 0) {
		
		   List<String> imagePaths = addImagesToSkill(skill, images, userName);
         skill.setImagePaths(imagePaths);
         }
	        
	skillService.UpdateSkill(skill, (String)session.getAttribute("userName"));
	return "redirect:/myprofile";
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
public String loadFromToAddSkills(HttpSession session,Model model) {
	
	return "createSkills";
}

@GetMapping("/skills/delete/{id}")
public String deleteSkill(@PathVariable Long id) {
	 Skill skillToDelete = skillService.getSkillById(id);
skillService.removeSkillById(id);  
deleteSkillImages(skillToDelete);
    return "redirect:/myprofile";
}
private void deleteSkillImages(Skill skill) {
    List<String> imagePaths = skill.getImagePaths();
    if (imagePaths != null) {
        for (String imagePath : imagePaths) {
            try {
                // Construct the file path of the image
                String filePath = "src/main/resources/static" + imagePath;
                
                // Create a file object
                File file = new File(filePath);
                
                // Check if the file exists and delete it
                if (file.exists()) {
                    file.delete();
                    System.out.println("File deleted successfully: " + filePath);
                }
            } catch (Exception e) {
                // Handle any exceptions that occur during file deletion
                e.printStackTrace();
            }
        }
    }
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