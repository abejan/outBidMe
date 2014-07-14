package com.spring.controllers.authentication;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *  Simle pojo representing the response model for the authentication service client.
 */
public class AuthResponse implements Serializable{

	private static final long serialVersionUID = 945452753250772878L;
	
	private boolean isAuthenticated;
	private String  authMessage;
	
	
	public AuthResponse(boolean isAuthenticated, String authMessage) {
		super();
		this.isAuthenticated = isAuthenticated;
		this.authMessage = authMessage;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public String getAuthMessage() {
		return authMessage;
	}

	public void setAuthMessage(String authMessage) {
		this.authMessage = authMessage;
	}
	
}
