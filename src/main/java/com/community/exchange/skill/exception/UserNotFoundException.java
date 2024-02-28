package com.community.exchange.skill.exception;


public class UserNotFoundException extends Exception {
	  private int statusCode;
	    private String message;
	 
	    public UserNotFoundException(String message)
	    {
	        super();
	        this.message = message;
	       
	    }
}
