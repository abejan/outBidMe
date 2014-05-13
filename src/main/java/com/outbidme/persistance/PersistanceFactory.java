package com.outbidme.persistance;

import com.outbidme.authentication.Account;
import com.outbidme.persistance.authentication.AccountGateway;

public class PersistanceFactory {


    public static PersistanceManager getPersistanceManager() {
        return InMemPersistanceManager.Instance;
    }

	public static AccountGateway getAccountGateway() {
		return new AccountGateway() {
			public void persist(Account account) throws PersistenceException {
				getPersistanceManager().persist(account);
			}

			public Account findAccountByUserName(String username) {
				return  getPersistanceManager().findEntity(getAccountMatcher(username), Account.class);
			}

			private EntityMatcher<Account> getAccountMatcher(final String username) {
				return new EntityMatcher<Account>() {
					public boolean matches(Account entity) {
						return username.equals(entity.getUsername());
					}
				};
			}
			
		};
	}

}
