package com.outbidme.persistance;

import com.outbidme.configuration.SystemConfiguration;
import com.outbidme.configuration.SystemConfiguration.Type;
import com.outbidme.persistance.dao.authentication.AccountDAO;
import com.outbidme.persistance.dao.impl.authentication.AccountDAOImpl;
import com.outbidme.persistance.dao.impl.notifications.MailBoxDAOImpl;
import com.outbidme.persistance.dao.impl.product.ProductDAOImpl;
import com.outbidme.persistance.dao.impl.product.UserBidDAOImpl;
import com.outbidme.persistance.dao.notifications.MailBoxDAO;
import com.outbidme.persistance.dao.product.ProductDAO;
import com.outbidme.persistance.dao.product.UserBidDAO;

/**
 * Responsible for creating implementations of your faveourite OutBidMe DAOs.
 */
public class PersistanceFactory {

	public static AccountDAO getAccountDataAccessObj() {
		return new AccountDAOImpl(getPersistanceManager());
	}

    public static ProductDAO getProductDataAccessObj() {
        return new ProductDAOImpl(getPersistanceManager());
    }

	public static UserBidDAO getUserBidDataAccessObj() {
		return new UserBidDAOImpl(getPersistanceManager());
	}
	
	public static MailBoxDAO getMailBoxDataAccessObj() {
		return new MailBoxDAOImpl(getPersistanceManager());
	}
	
	public static PersistanceManager getPersistanceManager() {
	    return (PersistanceManager) SystemConfiguration.Instance.getComponent(Type.Persistance);
	}
	
}
