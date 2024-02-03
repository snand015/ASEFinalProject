package com.community.exchange.skill.exception;


public class skillNotFoundException extends Exception {
	  private int statusCode;
	    private String message;
	 
	    public skillNotFoundException(String message)
	    {
	        super();
	        this.message = message;
	       
	    }
}
