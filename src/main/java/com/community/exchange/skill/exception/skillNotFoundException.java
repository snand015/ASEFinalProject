package com.community.exchange.skill.exception;


public class SkillNotFoundException extends Exception {
	  private int statusCode;
	    private String message;
	 
	    public SkillNotFoundException(String message)
	    {
	        super();
	        this.message = message;
	       
	    }
}
