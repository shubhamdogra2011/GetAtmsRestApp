package com.mobiquity.atms.exceptions;

public class InternalException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	String message;
	
	public InternalException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
