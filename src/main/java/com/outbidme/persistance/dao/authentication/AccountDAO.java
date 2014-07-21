package com.outbidme.persistance.dao.authentication;

import com.outbidme.model.authentication.Account;
import com.outbidme.persistance.PersistenceException;

public interface AccountDAO {
	
	public void persist(Account account) throws PersistenceException;
	
	public Account findAccountById(int accountId);

	public Account findAccountByUserName(String username);

	public void removeAccountWithUsername(String string);

	public int getNextValidId();

}
