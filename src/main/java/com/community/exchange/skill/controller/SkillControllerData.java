package com.community.exchange.skill.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.service.SkillService;
import com.community.exchange.skill.service.UserService;
@Service
public class SkillControllerData {
	

	public List<String> addImagesToSkill(Skill skill, MultipartFile[] images, String userName) {
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
	public void deleteSkillImages(Skill skill) {
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
}