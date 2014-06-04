package com.outbidme.persistance;

import com.outbidme.configuration.SystemConfiguration;
import com.outbidme.configuration.SystemConfiguration.Type;
import com.outbidme.model.authentication.Account;
import com.outbidme.model.product.Product;
import com.outbidme.persistance.authentication.AccountGateway;
import com.outbidme.persistance.product.ProductGateway;

public class PersistanceFactory {


    private static ProductGateway productGateway;

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

    public static ProductGateway getProductGateway() {
        return new ProductGateway() {

            public void persist(Product product) throws PersistenceException {
                getPersistanceManager().persist(product);
            }

            public double getNextValidId() {
                return getPersistanceManager().getEntityCount(Product.class) + 1;
            }

            public Product findEntity(final double id) {
                return getPersistanceManager().findEntity(new EntityMatcher<Product>() {
                    public boolean matches(Product entity) {
                        return entity.getId() == id;
                    }
                }, Product.class);
            }
        };
    }
}
