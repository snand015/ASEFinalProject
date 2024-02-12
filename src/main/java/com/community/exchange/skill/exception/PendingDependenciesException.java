package com.community.exchange.skill.exception;

@SuppressWarnings("serial")
public class PendingDependenciesException extends Exception {
	 public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private int statusCode;
	    private String message;
	 
	    public  PendingDependenciesException(String message)
	    {
	        super();
	        this.message = message;
	    }

}
