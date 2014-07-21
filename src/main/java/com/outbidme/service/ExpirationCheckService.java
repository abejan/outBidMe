package com.outbidme.service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.outbidme.configuration.eventbus.EventBusService;
import com.outbidme.configuration.eventbus.IEventBus;
import com.outbidme.model.notifications.BidMessage;
import com.outbidme.model.notifications.ProductExpiredMessage;
import com.outbidme.model.product.BidStatus;
import com.outbidme.model.product.Product;
import com.outbidme.model.product.UserBid;
import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.dao.product.ProductDAO;

/**
 * Schedules, or runs a job on demand responsible for checking whether products have expired or not, 
 * and sending the appropriate notifications for any subscribers which are interested in products.
 */
public class ExpirationCheckService {

	private final ProductDAO productGateway  = PersistanceFactory.getProductDataAccessObj();
//	private final NotificationsService notificationService = new NotificationsService();
//	private final BiddingService 	   biddingService	   = new BiddingService();
	
	private final IEventBus eventBus = new EventBusService().getEventBus();
	
	private final Callable<Void> expirationCheckTask = new Callable<Void>(){
		@Override
		public Void  call() throws Exception{
			List<Product> allProducts = productGateway.findAllProducts();
			for(Product product : allProducts){
				if(product.isExpired()){
//				   UserBid winnerBid = biddingService.getWinnerBid(product.getId());
//				   if(winnerBid != null)
//				      notificationService.sendMail(winnerBid.getAccountId(), new BidMessage(BidStatus.WIN, product.getId()));
                    eventBus.post(new ProductExpiredMessage(product.getId()));
				}
			}
			return null;
		}
	};
	
	
	/**
	 * Execute the product expiration check asynchronous with the calling thread.
	 * @return A simple handle object that can be used by the calling thread to wait for the completion of the 
	 *         expiration check job if really necessary. 
	 */
	public Future<Void> startExpirationCheck() {
		ExecutorService execService = Executors.newSingleThreadExecutor();
		Future<Void> result = execService.submit(expirationCheckTask);
		execService.shutdown();
		return result;
	}

	
}
