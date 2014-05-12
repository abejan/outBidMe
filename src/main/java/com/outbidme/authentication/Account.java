package com.outbidme.authentication;

public class Account {

	
	private String password;
	private String username;
	
	
	public Account(String username, String password) {
		this.password = password;
		this.username = username;
	}

	
	public Object getUsername() {
		return username;
	}
	
	
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	
	public String getPassword() {
		return password;
	}

}
