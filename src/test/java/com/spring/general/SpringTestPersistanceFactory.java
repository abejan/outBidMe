package com.spring.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.dao.authentication.AccountDAO;
import com.outbidme.persistance.dao.notifications.MailBoxDAO;
import com.outbidme.persistance.dao.product.ProductDAO;
import com.outbidme.persistance.dao.product.UserBidDAO;
import com.spring.persistance.SpringPersistanceFactory;


/**
 * An extension of the SpringPersistanceFactory plugin component of OutBidMe, used in tests which need to 
 * run in a specific Spring application context, so that autowiring and other goodies can work with JUnit.
 */
@ContextConfiguration( "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class SpringTestPersistanceFactory extends SpringPersistanceFactory{
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private UserBidDAO userBidDAO;
	
	@Autowired
	private MailBoxDAO mailBoxDAO;
	

	@Override
	public AccountDAO getAccountDataAccessObj() {
		return accountDAO;
	}

	@Override
	public ProductDAO getProductDataAccessObj() {
		return productDAO;
	}

	@Override
	public UserBidDAO getUserBidDataAccessObj() {
		return userBidDAO;
	}

	@Override
	public MailBoxDAO getMailBoxDataAccessObj() {
		return mailBoxDAO;
	}

	@Override
	public PersistanceManager getPersistanceManager() {
		return null;
	}
	
}
