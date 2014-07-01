package com.outbidme.persistance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.outbidme.configuration.SystemConfiguration;
import com.outbidme.configuration.SystemConfiguration.Type;
import com.outbidme.model.authentication.Account;
import com.outbidme.model.product.Product;
import com.outbidme.model.product.UserBid;
import com.outbidme.persistance.authentication.AccountGateway;
import com.outbidme.persistance.product.ProductGateway;
import com.outbidme.persistance.product.UserBidGateway;

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
				Collection<Account> results = getPersistanceManager().
									findEntities(getAccountMatcher(username), Account.class);
				if(!results.isEmpty())
					return results.iterator().next();
				
				return null;
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
        	
            public int getNextValidId() throws PersistenceException {
                return getPersistanceManager().getEntityCount(Product.class);
            }

            public Product findEntity(final double id) {
                Collection<Product> results = getPersistanceManager()
                						.findEntities(getProductMatcher(id), Product.class);
                if(!results.isEmpty())
					return results.iterator().next();
              return null;
            }

			private EntityMatcher<Product> getProductMatcher(final double id) {
				return new EntityMatcher<Product>() {
                    public boolean matches(Product entity) {
                        return entity.getId() == id;
                    }
                };
			}

        };
    }

	public static UserBidGateway getUserBidGateway() {
		return new UserBidGateway() {
			
			public void persist(UserBid userBid) throws PersistenceException {
				 getPersistanceManager().persist(userBid);
			}
			
			public int getNextValidId() throws PersistenceException {
				 return getPersistanceManager().getEntityCount(UserBid.class);
			}

			public void removeBid(final int id) {
				 getPersistanceManager().removeEntity(new EntityMatcher<UserBid>() {
					public boolean matches(UserBid entity) {
						return entity.getId() == id;
					}
				}, UserBid.class);
			}

			public List<UserBid> findAllBids(final int productId) {
				Collection<UserBid> results = getPersistanceManager().findEntities(new EntityMatcher<UserBid>() {
					public boolean matches(UserBid entity) {
						return entity.getProductId() == productId;
					}
				}, UserBid.class);
				return toList(results);
			}

		};
	}
	
	private static <T> List<T> toList(Collection<T> collection) {
		List<T> list = new ArrayList<T>();
		for(T bid : collection)
			list.add(bid);
		return list;
	}
}
