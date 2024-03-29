package com.community.exchange.skill.exception;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;
 
@ControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(value
                      = RegistractionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(RegistractionException ex,Model model)
    {
    	model.addAttribute("message",ex.getMessage());
    	model.addAttribute("status",HttpStatus.BAD_REQUEST);
       return "error";
    }
    

    @ExceptionHandler(value
                      = SkillNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String
    handleException(SkillNotFoundException ex,Model model)
    {
    	model.addAttribute("message",ex.getMessage());
    	model.addAttribute("status",HttpStatus.NOT_FOUND);
       return "error";

    }
    

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public  String handleException(UserNotFoundException ex,Model model)
    {
    	model.addAttribute("message",ex.getMessage());
    	model.addAttribute("status",HttpStatus.BAD_REQUEST);
       return "error";

    }
    @ExceptionHandler(value = UserUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public  String handleException(UserUpdateException ex,Model model)
    {
    	model.addAttribute("message",ex.getMessage());
    	model.addAttribute("status",HttpStatus.BAD_REQUEST);
       return "error";

    }
 
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    public  String handleException(PendingDependenciesException ex,Model model)
    {
    model.addAttribute("message",ex.getMessage());
    model.addAttribute("status",HttpStatus.BAD_REQUEST);
    return "error";

    }
}