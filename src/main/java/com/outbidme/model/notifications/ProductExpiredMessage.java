package com.outbidme.model.notifications;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductExpiredMessage {
	
	@Id
    private int expiredProductId;

    public ProductExpiredMessage(int expiredProductId) {
        this.expiredProductId = expiredProductId;
    }

    public int getExpiredProductId() {
        return expiredProductId;
    }
}
