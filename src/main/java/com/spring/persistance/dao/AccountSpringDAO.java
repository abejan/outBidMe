package com.spring.persistance.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.outbidme.model.authentication.Account;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.authentication.AccountDAO;


@Repository
public class AccountSpringDAO implements AccountDAO{

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public void persist(Account account) throws PersistenceException {
		em.persist(account);
	}

	@Override
	public Account findAccountById(int accountId) {
		return em.find(Account.class, accountId);
	}

	@Override
	public Account findAccountByUserName(String username) {
		return null;
	}

	@Override
	public void removeAccountWithUsername(String string) {
	}

	@Override
	public int getNextValidId() {
		return 0;
	}

}
