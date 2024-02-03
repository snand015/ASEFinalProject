package com.community.exchange.skill.exception;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;
 
@ControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(value
                      = RegistractionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity
    handleException(RegistractionException ex)
    {
        return new ResponseEntity(
             ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    

    @ExceptionHandler(value
                      = skillNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity
    handleException(skillNotFoundException ex)
    {
        return new ResponseEntity(
             ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    

    @ExceptionHandler(value
                      = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity
    handleException(UserNotFoundException ex)
    {
        return new ResponseEntity(
             ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}