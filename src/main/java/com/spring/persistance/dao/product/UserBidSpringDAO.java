package com.spring.persistance.dao.product;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.outbidme.model.product.UserBid;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.product.UserBidDAO;

public class UserBidSpringDAO implements UserBidDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persist(UserBid userBid) throws PersistenceException {
		em.persist(userBid);
	}

	@Override
	public int getNextValidId() throws PersistenceException {
		Query query = em.createQuery("SELECT MAX(bid.id) FROM UserBid bid");
		return (Integer)query.getSingleResult() + 1;
	}

	@Override
	public void removeBid(int id) {
		UserBid bid = em.find(UserBid.class, id);
		if(bid != null)
	  	   em.remove(bid);
	}

	@Override
	public List<UserBid> findAllBids(int productId) {
		TypedQuery<UserBid> query = em.createQuery("SELECT * FROM UserBid bid", UserBid.class);
		return query.getResultList();
	}

	@Override
	public void removeAllBids() {
		em.createQuery("DELETE bid FROM UserBid bid WHERE bid.id >= 0");
	}

}
