package com.outbidme.service;

import java.util.List;

import com.outbidme.model.product.Product;
import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.product.ProductGateway;

/**
 * Schedules, or runs a job on demand responsible for checking whether products have expired or not, 
 * and sending the appropriate notifications for any subscribers which are interested in products.
 */
public class ExpirationCheckService {

	private static final ProductGateway productGateway = PersistanceFactory.getProductGateway();
	
	public void runCheckJob() {
		List<Product> allProducts = productGateway.findAllProducts();
		for(Product product : allProducts){
			if(product.isExpired()){
				//TODO
			}
		}
	}

}
