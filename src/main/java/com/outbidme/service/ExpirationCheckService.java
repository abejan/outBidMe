package com.outbidme.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.outbidme.configuration.eventbus.EventBusService;
import com.outbidme.configuration.eventbus.IEventBus;
import com.outbidme.model.notifications.BidMessage;
import com.outbidme.model.product.BidStatus;
import com.outbidme.model.product.Product;
import com.outbidme.model.product.UserBid;
import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.product.ProductGateway;

/**
 * Schedules, or runs a job on demand responsible for checking whether products have expired or not, 
 * and sending the appropriate notifications for any subscribers which are interested in products.
 */
public class ExpirationCheckService {

	private final ProductGateway productGateway  = PersistanceFactory.getProductGateway();
	private final NotificationsService notificationService = new NotificationsService();
	private final BiddingService 	   biddingService	   = new BiddingService();
	
	private final IEventBus eventBus = new EventBusService().getEventBus();
	
	private final Runnable expirationCheckTask = new Runnable(){
		@Override
		public void run() {
			List<Product> allProducts = productGateway.findAllProducts();
			for(Product product : allProducts){
				if(product.isExpired()){
				   UserBid winnerBid = biddingService.getWinnerBid(product.getId());
				   if(winnerBid != null)
				      notificationService.sendMail(winnerBid.getAccountId(), new BidMessage(BidStatus.WIN, product.getId()));
				}
			}
		}
	};
	
	
	
	public void runCheckJob() {
		ExecutorService execService = Executors.newSingleThreadExecutor();
		execService.execute(expirationCheckTask);
		execService.shutdown();
	}

	
}
