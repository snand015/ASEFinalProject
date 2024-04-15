package com.community.exchange.skill.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RegistractionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRegistrationException(RegistractionException ex, Model model) {
        model.addAttribute("messageText", ex.getMessage());
        model.addAttribute("status", HttpStatus.BAD_REQUEST);
        return "error";
    }

    @ExceptionHandler(value = SkillNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleSkillNotFoundException(SkillNotFoundException ex, Model model) {
        model.addAttribute("messageText", ex.getMessage());
        model.addAttribute("status", HttpStatus.NOT_FOUND);
        return "error";
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserNotFoundException(UserNotFoundException ex, Model model) {
        model.addAttribute("messageText", ex.getMessage());
        model.addAttribute("status", HttpStatus.BAD_REQUEST);
        return "error";
    }

    @ExceptionHandler(value = UserUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserUpdateException(UserUpdateException ex, Model model) {
        model.addAttribute("messageText", ex.getMessage());
        model.addAttribute("status", HttpStatus.BAD_REQUEST);
        return "error";
    }

    @ExceptionHandler(value = PendingDependenciesException.class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    public String handlePendingDependenciesException(PendingDependenciesException ex, Model model) {
        model.addAttribute("messageText", ex.getMessage());
        model.addAttribute("status", HttpStatus.FAILED_DEPENDENCY);
        return "error";
    }
}
