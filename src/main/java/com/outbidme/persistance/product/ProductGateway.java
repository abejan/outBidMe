package com.outbidme.persistance.product;

import com.outbidme.model.product.Product;
import com.outbidme.persistance.PersistenceException;

public interface ProductGateway {
    public void persist(Product product) throws PersistenceException;

    public double getNextValidId();

    public Product findEntity(double id);
}
