package com.outbidme.service;

import java.util.Collections;
import java.util.List;

import com.outbidme.configuration.eventbus.EventBusService;
import com.outbidme.configuration.eventbus.IEventBus;
import com.outbidme.configuration.eventbus.IEventListener;
import com.outbidme.model.notifications.BidMessage;
import com.outbidme.model.notifications.ProductExpiredMessage;
import com.outbidme.model.product.BidStatus;
import com.outbidme.model.product.Product;
import com.outbidme.model.product.UserBid;
import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.product.ProductDAO;
import com.outbidme.persistance.dao.product.UserBidDAO;


/**
 * Service class which performs user bidding operations.
 */
public class BiddingService implements IEventListener{
	
    private final ProductDAO productGateway = PersistanceFactory.getProductDataAccessObj();
    private final IEventBus eventBus = new EventBusService().getEventBus();
    //private final NotificationsService notificationService = new NotificationsService();


    public BiddingService() {
        eventBus.register(this);
    }

    public UserBid placeBid(int accountId, int productId, double bidPrice) {
		  Product product = productGateway.findProduct(productId);
		  if(!canCreateBid(bidPrice, product, accountId))
		     return null;
		  
		   UserBid userBid = null;
		   try {
			   
			 removeCurrentWinBid(productId);
			 userBid = createAndPersistUserBid(accountId, productId, bidPrice);
			 
		   } catch (PersistenceException e) {
		   }
		   
	   return userBid;
	}

	public void removeUserBidOnProduct(int bidId) {
		UserBidDAO bidGateway = PersistanceFactory.getUserBidDataAccessObj();
		bidGateway.removeBid(bidId);
	}
	
	/**
	 * Returns the winning bid on the given product.
	 */
	public UserBid getWinnerBid(int productId) {
		UserBidDAO bidGateway = PersistanceFactory.getUserBidDataAccessObj();
		List<UserBid> bidsForProduct = bidGateway.findAllBids(productId);
        if (!bidsForProduct.isEmpty()) {
		    Collections.sort(bidsForProduct);
		    return bidsForProduct.iterator().next();
        }
        return null;
	}
	
	private UserBid createAndPersistUserBid(int accountId, int productId, double bidPrice) throws PersistenceException {
		  UserBidDAO bidGateway = PersistanceFactory.getUserBidDataAccessObj();
		  int id = bidGateway.getNextValidId();
		  
		  UserBid userBid = new UserBid(id, accountId, productId, bidPrice);
		  bidGateway.persist(userBid);
		  
		return userBid;
	}

	private void removeCurrentWinBid( int productId) {
		 UserBidDAO bidGateway = PersistanceFactory.getUserBidDataAccessObj();
		 List<UserBid> currentProductBids = bidGateway.findAllBids(productId);
		 for(UserBid currentBid : currentProductBids){
			 bidGateway.removeBid(currentBid.getId());
             eventBus.post(new BidMessage(BidStatus.OUTBID, productId, currentBid.getAccountId()));
			 //notificationService.sendMail(currentBid.getAccountId(), new BidMessage(BidStatus.OUTBID, productId));
		 }
	}

	/**
	 * Perform validations prior to creating a new user bid.
	 */
	private boolean canCreateBid(double bidPrice, Product product, int accountId) {
		
		if(product == null) return false;
		if(bidPrice < product.getPrice()) return false;
		
		UserBid currentWinningBid = getWinnerBid(product.getId());
		if(currentWinningBid != null){
		   if(currentWinningBid.getAccountId() == accountId) return false;	
		   if(currentWinningBid.getPrice() >= bidPrice) return false;	
		}
	
		return true;
	}

    @Override
    public void handle(Object event) {
        final ProductExpiredMessage productExpiredMessage = (ProductExpiredMessage) event;
        final UserBid winningBid = getWinnerBid(productExpiredMessage.getExpiredProductId());
        eventBus.post(new BidMessage(BidStatus.WIN, winningBid.getProductId(), winningBid.getAccountId()));
    }
}
