package com.outbidme.persistance.product;

import java.util.List;

import com.outbidme.model.product.Product;
import com.outbidme.persistance.PersistenceException;

public interface ProductGateway {
    public void persist(Product product) throws PersistenceException;

    public int getNextValidId() throws PersistenceException;

    public Product findProduct(double id);
    
    public List<Product> findAllProducts();
}
