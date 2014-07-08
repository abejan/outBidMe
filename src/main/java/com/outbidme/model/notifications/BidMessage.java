package com.outbidme.model.notifications;

import com.outbidme.model.product.BidStatus;

public class BidMessage implements Message{

	private BidStatus status;
	private int productId = -1;
	
	public BidMessage(BidStatus status, int productId){
		this.status = status;
		this.productId  = productId;
	}
	
	public BidStatus getStatus() {
		return status;
	}

	public int getProductId() {
		return productId;
	}

}
