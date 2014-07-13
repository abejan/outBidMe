package com.outbidme.model.notifications;

import com.outbidme.model.product.Product;

public class ProductExpiredMessage {
    private int expiredProductId;

    public ProductExpiredMessage(int expiredProductId) {
        this.expiredProductId = expiredProductId;
    }

    public int getExpiredProductId() {
        return expiredProductId;
    }
}
