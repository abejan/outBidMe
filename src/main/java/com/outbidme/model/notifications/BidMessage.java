package com.outbidme.model.notifications;

import com.outbidme.model.product.BidStatus;

public class BidMessage {

	private BidStatus status;
	
	public BidMessage(BidStatus status){
		this.status = status;
	}
	
	public BidStatus getStatus() {
		return status;
	}

}
