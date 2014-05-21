package com.outbidme.presentation.authentication;

public enum AuthenticationMessages {

	SUCCESS("Login succesful"), FAIL("Login failed");
	
	private String message;

	AuthenticationMessages(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
