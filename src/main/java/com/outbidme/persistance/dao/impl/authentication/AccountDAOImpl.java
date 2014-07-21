package com.outbidme.persistance.dao.impl.authentication;

import java.util.Collection;

import com.outbidme.model.authentication.Account;
import com.outbidme.persistance.EntityMatcher;
import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.authentication.AccountDAO;
import com.outbidme.persistance.dao.impl.AbstractEntityDAO;

public class AccountDAOImpl extends AbstractEntityDAO implements AccountDAO{

	
	public AccountDAOImpl(PersistanceManager persistanceManager) {
		super(persistanceManager);
	}

	public void persist(Account account) throws PersistenceException {
		getPersistanceManager().persist(account);
	}

	public Account findAccountByUserName(String username) {
		Collection<Account> results = getPersistanceManager().
							findEntities(getAccountNameMatcher(username), Account.class);
		return getFirstElement(results);
	}

	@Override
	public Account findAccountById(int accountId) {
		Collection<Account> results = getPersistanceManager().
				findEntities(getAccountIdeMatcher(accountId), Account.class);
		return getFirstElement(results);
	}
	
	private EntityMatcher<Account> getAccountNameMatcher(final String username) {
		return new EntityMatcher<Account>() {
			public boolean matches(Account entity) {
				return username.equals(entity.getUsername());
			}
		};
	}

	private EntityMatcher<Account> getAccountIdeMatcher(final int id) {
		return new EntityMatcher<Account>() {
			public boolean matches(Account entity) {
				return id == entity.getId();
			}
		};
	}
	
	public void removeAccountWithUsername(String username) {
		getPersistanceManager().removeEntity(getAccountNameMatcher(username), Account.class);
	}

	@Override
	public int getNextValidId() {
		return getPersistanceManager().getEntityCount(Account.class);
	}
	
}
