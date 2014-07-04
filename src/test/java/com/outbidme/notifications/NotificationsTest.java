package com.outbidme.notifications;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.outbidme.general.AbstractTest;
import com.outbidme.general.TestUtils;
import com.outbidme.model.notifications.BidMessage;
import com.outbidme.model.notifications.MailBox;
import com.outbidme.model.notifications.Message;
import com.outbidme.model.product.BidStatus;
import com.outbidme.model.product.UserBid;
import com.outbidme.service.BiddingService;
import com.outbidme.service.NotificationsService;

public class NotificationsTest extends AbstractTest{

	private final static BiddingService 	  biddingService = new BiddingService();
	private final static NotificationsService notifyService  = new NotificationsService();
	
	
	@Test
    public void verify_user_is_notified_when_putbid(){
		
		UserBid loosingBid = biddingService.placeBid(TestUtils.TEST_USERNAME_2, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 1);
		UserBid winningBid = biddingService.placeBid(TestUtils.TEST_USERNAME, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 2);
    
    	MailBox userMailBox = notifyService.getAccountMailBox(TestUtils.TEST_USERNAME_2);
    	Message outbidMessage = userMailBox.getLastMessage();
    	
    	assertTrue(outbidMessage instanceof BidMessage);
    	assertTrue(((BidMessage)outbidMessage).getStatus() == BidStatus.OUTBID);
    	
    	//cleanup
    	biddingService.removeUserBidOnProduct(winningBid.getId());
    	biddingService.removeUserBidOnProduct(loosingBid.getId());
    }
	
	
	@Test
    public void verify_user_is_notified_when_winning_a_bid(){
		
		UserBid loosingBid = biddingService.placeBid(TestUtils.TEST_USERNAME_2, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 1);
		UserBid winningBid = biddingService.placeBid(TestUtils.TEST_USERNAME, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 2);
    
    	MailBox userMailBox = notifyService.getAccountMailBox(TestUtils.TEST_USERNAME);
    	Message winMessage = userMailBox.getLastMessage();
    	
    	assertTrue(winMessage instanceof BidMessage);
    	assertTrue(((BidMessage)winMessage).getStatus() == BidStatus.WIN);
    	
    	//cleanup
    	biddingService.removeUserBidOnProduct(winningBid.getId());
    	biddingService.removeUserBidOnProduct(loosingBid.getId());
    }
	
}