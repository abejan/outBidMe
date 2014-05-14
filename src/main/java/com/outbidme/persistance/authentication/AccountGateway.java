package com.outbidme.persistance.authentication;

import com.outbidme.authentication.Account;
import com.outbidme.persistance.PersistenceException;

public interface AccountGateway {
	
	public void persist(Account account) throws PersistenceException;

	public Account findAccountByUserName(String username);

	public void removeAccountWithUsername(String string);

}
