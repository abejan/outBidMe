package com.outbidme.persistance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.outbidme.configuration.SystemConfiguration;
import com.outbidme.configuration.SystemConfiguration.Type;
import com.outbidme.model.authentication.Account;
import com.outbidme.model.notifications.MailBox;
import com.outbidme.model.product.Product;
import com.outbidme.model.product.UserBid;
import com.outbidme.persistance.authentication.AccountGateway;
import com.outbidme.persistance.notifications.MailBoxGateway;
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

            public Product findProduct(final double id) {
                Collection<Product> results = getPersistanceManager()
                						.findEntities(getProductIdMatcher(id), Product.class);
              return getFirstElement(results);
            }

			@Override
			public List<Product> findAllProducts() {
				 Collection<Product> results = getPersistanceManager().findEntities(getAllEntitiesMatcher(Product.class), Product.class);
				 return toList(results);
			}

			private EntityMatcher<Product> getProductIdMatcher(final double id) {
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

			@Override
			public void removeBidForProduct(final int productId) {
				 getPersistanceManager().removeEntity(new EntityMatcher<UserBid>() {
						public boolean matches(UserBid entity) {
							return entity.getProductId() == productId;
						}
				}, UserBid.class);
			}
			
			@Override
			public void removeAllBids() {
				 getPersistanceManager().removeEntity(getAllEntitiesMatcher(UserBid.class), UserBid.class);
			}

		};
	}
	
	public static MailBoxGateway getMailBoxGateway() {
		return new MailBoxGateway(){

			@Override
			public MailBox findMailBoxForAccount(final int accountId) {
				Collection<MailBox> results = getPersistanceManager().findEntities(new EntityMatcher<MailBox>() {
					@Override
					public boolean matches(MailBox entity) {
						return entity.getAccountId() == accountId;
					}
				}, MailBox.class);
				return getFirstElement(results);
			}

			@Override
			public void persist(MailBox mb) throws PersistenceException {
				 getPersistanceManager().persist(mb);
			}
			
		};
	}
	
	private static <T> List<T> toList(Collection<T> collection) {
		List<T> list = new ArrayList<T>();
		for(T bid : collection)
			list.add(bid);
		return list;
	}
	
	private static <T> T getFirstElement(Collection<T> collection) {
		 if(!collection.isEmpty())
				return collection.iterator().next();
		 return null;
	}
	
	private static <T> EntityMatcher<T> getAllEntitiesMatcher(Class<T> forClass){
		return new EntityMatcher<T>() {
			@Override
			public boolean matches(T entity) {
				return true;
			}
		};
	}
}
