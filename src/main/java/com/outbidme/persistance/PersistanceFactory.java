package com.outbidme.persistance;

import com.outbidme.persistance.dao.authentication.AccountDAO;
import com.outbidme.persistance.dao.notifications.MailBoxDAO;
import com.outbidme.persistance.dao.product.ProductDAO;
import com.outbidme.persistance.dao.product.UserBidDAO;


/**
 * Responsible for creating implementations of your faveourite OutBidMe DAOs.
 * This will be treated as a pluginable component of OutBidMe. The default implemenations resides in the 
 * "com.outbidme_default_impls" package which can be used for testing without a Database component or an ORM framework.
 */
public interface PersistanceFactory {

	public AccountDAO getAccountDataAccessObj();
	
    public ProductDAO getProductDataAccessObj();
    
	public UserBidDAO getUserBidDataAccessObj();
	
	public MailBoxDAO getMailBoxDataAccessObj();

	public PersistanceManager getPersistanceManager();
	
}
