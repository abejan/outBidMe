package com.outbidme.presentation.authentication;

public enum AuthenticationMessages {

	LOGIN_SUCCESS("Login successful"), LOGIN_FAIL("Login failed"),
    LOGOUT_SUCCESS("Logout successful"), LOGOUT_FAIL("Logout failed");
	
	private String message;

	AuthenticationMessages(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
