package com.outbidme_default_impls.persistance.dao.product;

import java.util.Collection;
import java.util.List;

import com.outbidme.model.product.UserBid;
import com.outbidme.persistance.EntityMatcher;
import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.product.UserBidDAO;
import com.outbidme_default_impls.persistance.dao.AbstractEntityDAO;

public class UserBidDAOImpl extends AbstractEntityDAO implements UserBidDAO{

	public UserBidDAOImpl(PersistanceManager persistanceManager) {
		super(persistanceManager);
	}

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
	
}
