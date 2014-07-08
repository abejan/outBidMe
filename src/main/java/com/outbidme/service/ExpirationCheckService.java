package com.outbidme.service;

import java.util.List;

import com.outbidme.model.notifications.BidMessage;
import com.outbidme.model.product.BidStatus;
import com.outbidme.model.product.Product;
import com.outbidme.model.product.UserBid;
import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.product.ProductGateway;
import com.outbidme.persistance.product.UserBidGateway;

/**
 * Schedules, or runs a job on demand responsible for checking whether products have expired or not, 
 * and sending the appropriate notifications for any subscribers which are interested in products.
 */
public class ExpirationCheckService {

	private static final ProductGateway productGateway  = PersistanceFactory.getProductGateway();
	private static final UserBidGateway bidsGateway     = PersistanceFactory.getUserBidGateway();
	
	private static final NotificationsService notificationService = new NotificationsService();
	private static final BiddingService biddingService = new BiddingService();
	
	
	public void runCheckJob() {
		List<Product> allProducts = productGateway.findAllProducts();
		for(Product product : allProducts){
			if(product.isExpired()){
			   UserBid winnerBid = biddingService.getWinnerBid(product.getId());
			   if(winnerBid != null)
			      notificationService.sendMail(winnerBid.getUserName(), new BidMessage(BidStatus.WIN));
			}
		}
	}

}
