package com.outbidme.bidding;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.outbidme.general.AbstractTest;
import com.outbidme.general.TestUtils;
import com.outbidme.model.product.UserBid;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.product.UserBidDAO;
import com.outbidme.service.BiddingService;

public class BiddingTest extends AbstractTest{

	/** Always perform cleanup of any newly added bids in tests to avoid test failures */
	
	private final static BiddingService biddingService = new BiddingService();
	private final UserBidDAO biddingGateway = persistanceFactory.getUserBidDataAccessObj();
	
	
	
	@Test
	public void can_bid_on_product_if_price_higher_or_equal_than_product_price(){
		//test with price equal
		UserBid userBid = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID, 
											TestUtils.TEST_PRODUCT_ID, TestUtils.TEST_PRODUCT_PRICE);

		assertTrue(persistanceFactory.getPersistanceManager().contains(userBid));
		biddingService.removeUserBidOnProduct(userBid.getId());
		
		//test with price higher
		userBid = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID, 
				TestUtils.TEST_PRODUCT_ID, TestUtils.TEST_PRODUCT_PRICE + 1);
		
		assertTrue(persistanceFactory.getPersistanceManager().contains(userBid));
		biddingService.removeUserBidOnProduct(userBid.getId());
	}

	@Test
	public void can_not_bid_on_products_if_price_lower_than_product_price(){
		//test with price lower
		UserBid userBid = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID, 
				TestUtils.TEST_PRODUCT_ID, TestUtils.TEST_PRODUCT_PRICE - 1);
		
		assertTrue(userBid == null);
	}
	
	@Test
	public void can_unbid_on_product(){
		
		final int testId = 5;
		final UserBid testUserBid = new UserBid(testId, TestUtils.TEST_ACCOUNT_ID, TestUtils.TEST_PRODUCT_ID, 500);
		
		try {
			biddingGateway.persist(testUserBid);
		} catch (PersistenceException e) {
			fail(e.getMessage());
		}
		biddingService.removeUserBidOnProduct(testId);
		
		assertTrue(!persistanceFactory.getPersistanceManager().contains(testUserBid));
		
	}

	@Test
	public void can_not_place_multiple_bids_by_same_user_if_he_is_already_winning(){
		UserBid bid1 = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID, TestUtils.TEST_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 1);
		UserBid bid2 = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID, TestUtils.TEST_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 2);
		
		assertTrue(bid2 == null);
		assertTrue(persistanceFactory.getPersistanceManager().contains(bid1));
		
		//cleanup
		biddingService.removeUserBidOnProduct(bid1.getId());
	}
	
	@Test
	public void can_not_place_bids_with_price_lower_than_current_winning_bid(){
		UserBid bid1 = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID, TestUtils.TEST_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 2);
		UserBid bid2 = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID_2, TestUtils.TEST_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 1);
		
		assertTrue(bid2 == null);
		assertTrue(persistanceFactory.getPersistanceManager().contains(bid1));
		
		//cleanup
		biddingService.removeUserBidOnProduct(bid1.getId());
	}
	
	@Test
	public void can_place_multiple_bids_on_same_product_by_different_users_if_price_is_increasing(){
		
		UserBid bid1 = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID, TestUtils.TEST_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 1);
		assertTrue(persistanceFactory.getPersistanceManager().contains(bid1));
		
		
		//next bid will replace old winning bid
		UserBid bid2 = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID_2, TestUtils.TEST_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 2);
		assertTrue(persistanceFactory.getPersistanceManager().contains(bid2));
		
		//cleanup
		biddingService.removeUserBidOnProduct(bid1.getId());
		biddingService.removeUserBidOnProduct(bid2.getId());
	}
	
	@Test
	public void can_win_bid_when_price_is_highest_and_product_time_expires(){

		UserBid bid1 = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 1);
		UserBid bid2 = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID_2, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 2);
		
		assertTrue(biddingService.getWinnerBid(TestUtils.TEST_EXPIRED_PRODUCT_ID).equals(bid2));
		
		//cleanup
		biddingService.removeUserBidOnProduct(bid1.getId());
		biddingService.removeUserBidOnProduct(bid2.getId());
        assertTrue(biddingGateway.findAllBids(TestUtils.TEST_EXPIRED_PRODUCT_ID).size() == 0);

	}

    @Test
    public void cannot_get_winner_bid_when_product_has_no_bids(){
        assertTrue(biddingGateway.findAllBids(TestUtils.TEST_EXPIRED_PRODUCT_ID).size() == 0);
        assertTrue(biddingService.getWinnerBid(TestUtils.TEST_EXPIRED_PRODUCT_ID) == null);
    }

}
