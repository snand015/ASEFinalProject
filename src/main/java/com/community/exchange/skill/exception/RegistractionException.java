package com.community.exchange.skill.exception;


public class RegistractionException extends Exception {
	
	
	 
	    private int statusCode;
	    private String message;
	 
	    public RegistractionException(String message)
	    {
	        super();
	        this.message = message;
	    }
	}

