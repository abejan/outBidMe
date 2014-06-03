package com.outbidme.persistance;

import com.outbidme.configuration.SystemConfiguration;
import static  com.outbidme.configuration.SystemConfiguration.Type;

import com.outbidme.model.authentication.Account;
import com.outbidme.persistance.authentication.AccountGateway;

public class PersistanceFactory {


    public static PersistanceManager getPersistanceManager() {
        return (PersistanceManager) SystemConfiguration.Instance.getComponent(Type.Persistance);
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

			public void removeAccountWithUsername(String username) {
				getPersistanceManager().removeEntity(getAccountMatcher(username), Account.class);
			}
			
		};
	}

}
