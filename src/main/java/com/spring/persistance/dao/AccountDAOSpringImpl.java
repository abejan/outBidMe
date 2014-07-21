package com.spring.persistance.dao;

import org.springframework.stereotype.Repository;

import com.outbidme.model.authentication.Account;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.authentication.AccountDAO;


@Repository
public class AccountDAOSpringImpl implements AccountDAO{

	@Override
	public void persist(Account account) throws PersistenceException {
		
	}

	@Override
	public Account findAccountById(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAccountWithUsername(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNextValidId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
