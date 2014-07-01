package com.outbidme.bidding;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.outbidme.general.AbstractTest;
import com.outbidme.general.TestUtils;
import com.outbidme.model.product.UserBid;
import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.product.UserBidGateway;
import com.outbidme.service.BiddingService;

public class BiddingTest extends AbstractTest{

	private final static BiddingService biddingService = new BiddingService();
	private final static UserBidGateway biddingGateway = PersistanceFactory.getUserBidGateway();
	
	
	@Test
	public void can_bid_on_product_if_price_higher_or_equal_than_product_price(){
		//test with price equal
		UserBid userBid = biddingService.placeBid(TestUtils.TEST_USERNAME, 
											TestUtils.TEST_EXPIRED_PRODUCT_ID, TestUtils.TEST_EXPIRED_PRODUCT_PRICE);

		assertTrue(PersistanceFactory.getPersistanceManager().contains(userBid));
		
		//test with price higher
		userBid = biddingService.placeBid(TestUtils.TEST_USERNAME, 
				TestUtils.TEST_EXPIRED_PRODUCT_ID, TestUtils.TEST_EXPIRED_PRODUCT_PRICE + 1);
		
		assertTrue(PersistanceFactory.getPersistanceManager().contains(userBid));
	}

	@Test
	public void can_not_bid_on_products_if_price_lower_than_product_price(){
		//test with price lower
		UserBid userBid = biddingService.placeBid(TestUtils.TEST_USERNAME, 
				TestUtils.TEST_EXPIRED_PRODUCT_ID, TestUtils.TEST_EXPIRED_PRODUCT_PRICE - 1);
		
		assertTrue(userBid == null);
	}
	
	@Test
	public void can_unbid_on_product(){
		
		final int testId = 5;
		final UserBid testUserBid = new UserBid(testId, "", TestUtils.TEST_EXPIRED_PRODUCT_ID, 500);
		
		try {
			biddingGateway.persist(testUserBid);
		} catch (PersistenceException e) {
			fail(e.getMessage());
		}
		
		biddingService.removeUserBidOnProduct(testId);
		
		assertTrue(!PersistanceFactory.getPersistanceManager().contains(testUserBid));
	}

	@Test
	public void can_place_multiple_bids_on_same_product(){
		
		UserBid bid1 = biddingService.placeBid(TestUtils.TEST_USERNAME, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_EXPIRED_PRODUCT_PRICE + 1);
		UserBid bid2 = biddingService.placeBid(TestUtils.TEST_USERNAME, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_EXPIRED_PRODUCT_PRICE + 2);
		
		assertTrue(PersistanceFactory.getPersistanceManager().contains(bid1));
		assertTrue(PersistanceFactory.getPersistanceManager().contains(bid2));
		
		//cleanup
		biddingService.removeUserBidOnProduct(bid2.getId());
		biddingService.removeUserBidOnProduct(bid2.getId());
	}
	
	@Test
	public void can_win_bid_when_price_is_highest_and_product_time_expires(){
		UserBid bid1 = biddingService.placeBid(TestUtils.TEST_USERNAME, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_EXPIRED_PRODUCT_PRICE + 2);
		UserBid bid2 = biddingService.placeBid(TestUtils.TEST_USERNAME, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_EXPIRED_PRODUCT_PRICE + 1);
		
		assertTrue(biddingService.getWinnerBid(TestUtils.TEST_EXPIRED_PRODUCT_ID).equals(bid1));
		
		//cleanup
		biddingService.removeUserBidOnProduct(bid1.getId());
		biddingService.removeUserBidOnProduct(bid2.getId());
	}
}
