package com.outbidme.model.notifications;

import com.outbidme.model.product.BidStatus;

public class BidMessage implements Message{

	private BidStatus status;
	private int productId = -1;
    private int accountId = -1;
	
	public BidMessage(BidStatus status, int productId, int accountId){
		this.status = status;
		this.productId  = productId;
        this.accountId = accountId;
	}
	
	public BidStatus getStatus() {
		return status;
	}

	public int getProductId() {
		return productId;
	}

    public int getAccountId() {
        return accountId;
    }
}
