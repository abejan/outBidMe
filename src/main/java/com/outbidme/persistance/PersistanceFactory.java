package com.outbidme.persistance;

import com.outbidme.authentication.Account;
import com.outbidme.persistance.authentication.AccountPersistor;

public class PersistanceFactory {

	
	public static PersistanceManager getPersistanceManager() {
		return InMemPersistanceManager.Instance;
	}
	
	
	public static AccountPersistor getAccountPersistor() {
		return new AccountPersistor() {
			public void persist(Account account) {
				InMemPersistanceManager.Instance.persist(account);
			}
		};
	}

}
