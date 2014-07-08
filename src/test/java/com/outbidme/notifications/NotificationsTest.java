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
import com.outbidme.service.ExpirationCheckService;
import com.outbidme.service.NotificationsService;

public class NotificationsTest extends AbstractTest{

	private final static BiddingService 	  biddingService = new BiddingService();
	private final static NotificationsService notifyService  = new NotificationsService();
	
	
	@Test
    public void verify_user_is_notified_when_putbid(){
		
		UserBid loosingBid = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID_2, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 1);
		UserBid winningBid = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 2);
    
    	MailBox userMailBox   = notifyService.getAccountMailBox(TestUtils.TEST_ACCOUNT_ID_2);
    	Message outbidMessage = userMailBox.getLastMessage();
    	
    	assertTrue(outbidMessage instanceof BidMessage);
    	assertTrue(((BidMessage)outbidMessage).getStatus() == BidStatus.OUTBID);
    	
    	//cleanup
    	biddingService.removeUserBidOnProduct(winningBid.getId());
    	biddingService.removeUserBidOnProduct(loosingBid.getId());
    }
	
	@Test
    public void verify_user_is_notified_when_winning_a_bid(){
		
		UserBid loosingBid = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID_2, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 1);
		UserBid winningBid = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID, TestUtils.TEST_EXPIRED_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 2);
    
		ExpirationCheckService expireService = new ExpirationCheckService();
		expireService.runCheckJob();
		
    	MailBox userMailBox = notifyService.getAccountMailBox(TestUtils.TEST_ACCOUNT_ID);
    	Message winMessage  = userMailBox.getLastMessage();
    	
    	assertTrue(winMessage instanceof BidMessage);
    	assertTrue(((BidMessage)winMessage).getStatus() == BidStatus.WIN);
    	
    	//cleanup
    	biddingService.removeUserBidOnProduct(winningBid.getId());
    	biddingService.removeUserBidOnProduct(loosingBid.getId());
    }
	
	@Test
	public void verify_user_is_not_notified_of_winning_bid_when_product_has_not_expired_yet(){
		UserBid winningBid = biddingService.placeBid(TestUtils.TEST_ACCOUNT_ID, TestUtils.TEST_PRODUCT_ID,  TestUtils.TEST_PRODUCT_PRICE + 2);
		
		MailBox userMailBox = notifyService.getAccountMailBox(TestUtils.TEST_ACCOUNT_ID);
		if(!userMailBox.isEmpty()){
			Message lastMessage  = userMailBox.getLastMessage();
			if(lastMessage instanceof BidMessage){
			   BidMessage bidMessage = (BidMessage)lastMessage;
			   int lastMessageProductId = bidMessage.getProductId();
			   if (lastMessageProductId == TestUtils.TEST_PRODUCT_ID){
				    assertTrue(bidMessage.getStatus() != BidStatus.WIN);
			   }
			   //test can pass if last message is not associated to the product being bidded.
			}
			//test can pass if last message is not a bid related message
		}
		//test can pass if mailbox is empty
		
		//cleanup
		biddingService.removeUserBidOnProduct(winningBid.getId());
	}
	
}
