package com.spring.persistance.dao.authentication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
		TypedQuery<Account> query = em.createQuery("SELECT ac FROM Account ac WHERE ac.username = :username",  Account.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}

	@Override
	public void removeAccountWithUsername(String username) {
		Account entity = em.find(Account.class, username);
		if(entity != null)
		   em.remove(entity);
	}

	@Override
	public int getNextValidId() {
		Query query = em.createQuery("SELECT MAX(ac.id) FROM Account ac");
		return (Integer)query.getSingleResult() + 1;
	}

}
