package com.outbidme.persistance.authentication;

import com.outbidme.authentication.Account;

public interface AccountGateway {
	
	public void persist(Account account);

	public Account findAccountByUserName(String username);

}
