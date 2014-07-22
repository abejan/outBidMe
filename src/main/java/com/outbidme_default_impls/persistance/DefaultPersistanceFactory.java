package com.outbidme_default_impls.persistance;

import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.dao.authentication.AccountDAO;
import com.outbidme.persistance.dao.notifications.MailBoxDAO;
import com.outbidme.persistance.dao.product.ProductDAO;
import com.outbidme.persistance.dao.product.UserBidDAO;
import com.outbidme_default_impls.persistance.dao.authentication.AccountDAOImpl;
import com.outbidme_default_impls.persistance.dao.notifications.MailBoxDAOImpl;
import com.outbidme_default_impls.persistance.dao.product.ProductDAOImpl;
import com.outbidme_default_impls.persistance.dao.product.UserBidDAOImpl;
import com.spring.persistance.InMemPersistanceManager;

/**
 * Responsible for creating implementations of your faveourite OutBidMe DAOs.
 */
public class DefaultPersistanceFactory implements PersistanceFactory{

	
	public AccountDAO getAccountDataAccessObj() {
		return new AccountDAOImpl(getPersistanceManager());
	}

    public ProductDAO getProductDataAccessObj() {
        return new ProductDAOImpl(getPersistanceManager());
    }

	public UserBidDAO getUserBidDataAccessObj() {
		return new UserBidDAOImpl(getPersistanceManager());
	}
	
	public MailBoxDAO getMailBoxDataAccessObj() {
		return new MailBoxDAOImpl(getPersistanceManager());
	}
	
	public PersistanceManager getPersistanceManager() {
	    return InMemPersistanceManager.Instance;
	}
	
}
