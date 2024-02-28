package com.community.exchange.skill.exception;

public class UserUpdateException extends Exception {
	 private int statusCode;
	    private String message;
	 
	    public UserUpdateException(String message)
	    {
	        super();
	        this.message = message;
	       
	    }
}
