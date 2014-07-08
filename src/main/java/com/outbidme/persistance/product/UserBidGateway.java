package com.outbidme.persistance.product;

import java.util.List;

import com.outbidme.model.product.UserBid;
import com.outbidme.persistance.PersistenceException;

public interface UserBidGateway {

	public void persist(UserBid userBid) throws PersistenceException;

	public int getNextValidId() throws PersistenceException;

	public void removeBid(int id);

	public List<UserBid> findAllBids(int productId);
	
	public void removeBidForProduct(int productId);

	public void removeAllBids();

}
