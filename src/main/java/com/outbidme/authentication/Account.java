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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (username != null ? !username.equals(account.username) : account.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

	@Override
	public String toString() {
		return "Account [username=" + username + "]";
	}
    
}
