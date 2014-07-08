package com.outbidme.service;

import java.util.Collections;
import java.util.List;

import com.outbidme.model.notifications.BidMessage;
import com.outbidme.model.product.BidStatus;
import com.outbidme.model.product.Product;
import com.outbidme.model.product.UserBid;
import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.product.ProductGateway;
import com.outbidme.persistance.product.UserBidGateway;


/**
 * Service class which performs user bidding operations.
 */
public class BiddingService {
	
    private final ProductGateway productGateway = PersistanceFactory.getProductGateway();
    private final NotificationsService notificationService = new NotificationsService();
    
	public UserBid placeBid(String userName, int productId, double bidPrice) {
		  Product product = productGateway.findProduct(productId);
		  if(!canCreateBid(bidPrice, product, userName))
		     return null;
		  
		   UserBid userBid = null;
		   try {
			   
			 removeCurrentWinBid(userName, productId);  
			 userBid = createAndPersistUserBid(userName, productId, bidPrice);
			 
		   } catch (PersistenceException e) {
		   }
		   
	   return userBid;
	}

	public void removeUserBidOnProduct(int bidId) {
		UserBidGateway bidGateway = PersistanceFactory.getUserBidGateway();
		bidGateway.removeBid(bidId);
	}
	
	/**
	 * Returns the winning bid on the given product.
	 */
	public UserBid getWinnerBid(int productId) {
		UserBidGateway bidGateway = PersistanceFactory.getUserBidGateway();
		List<UserBid> bidsForProduct = bidGateway.findAllBids(productId);
        if (!bidsForProduct.isEmpty()) {
		    Collections.sort(bidsForProduct);
		    return bidsForProduct.iterator().next();
        }
        return null;
	}
	
	private UserBid createAndPersistUserBid(String username, int productId, double bidPrice) throws PersistenceException {
		  UserBidGateway bidGateway = PersistanceFactory.getUserBidGateway();
		  int id = bidGateway.getNextValidId();
		  
		  UserBid userBid = new UserBid(id, username, productId, bidPrice);
		  bidGateway.persist(userBid);
		  
		return userBid;
	}

	private void removeCurrentWinBid(String username, int productId) {
		 UserBidGateway bidGateway = PersistanceFactory.getUserBidGateway();
		 List<UserBid> currentProductBids = bidGateway.findAllBids(productId);
		 for(UserBid currentBid : currentProductBids){
			 bidGateway.removeBid(currentBid.getId());
			 notificationService.sendMail(currentBid.getUserName(), new BidMessage(BidStatus.OUTBID, productId));
		 }
	}

	/**
	 * Perform validations prior to creating a new user bid.
	 */
	private boolean canCreateBid(double bidPrice, Product product, String userName) {
		
		if(product == null) return false;
		if(bidPrice < product.getPrice()) return false;
		
		UserBid currentWinningBid = getWinnerBid(product.getId());
		if(currentWinningBid != null){
		   if(currentWinningBid.getUserName().equals(userName)) return false;	
		   if(currentWinningBid.getPrice() >= bidPrice) return false;	
		}
	
		return true;
	}
}
