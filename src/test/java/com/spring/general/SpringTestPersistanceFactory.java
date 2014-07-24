package com.spring.general;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.dao.authentication.AccountDAO;
import com.outbidme.persistance.dao.notifications.MailBoxDAO;
import com.outbidme.persistance.dao.product.ProductDAO;
import com.outbidme.persistance.dao.product.UserBidDAO;
import com.spring.persistance.SpringPersistanceManagerAdapter;


/**
 * An extension of the SpringPersistanceFactory plugin component of OutBidMe, used in tests which need to 
 * run in a specific Spring application context, so that autowiring and other goodies can work with JUnit.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class SpringTestPersistanceFactory implements PersistanceFactory{
	
	/** This cached instance exists because we it is initialized and autowired by SpringJUnit4ClassRunner, and 
	we want to reuse it in other tests aswell (potentially non Spring context related) */
	private static SpringTestPersistanceFactory cachedInstance = new SpringTestPersistanceFactory();
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private UserBidDAO userBidDAO;
	
	@Autowired
	private MailBoxDAO mailBoxDAO;

	@PersistenceContext
	private EntityManager em;
	

	
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
		SpringPersistanceManagerAdapter pm = new SpringPersistanceManagerAdapter();
		pm.setEntityManager(em);
		return pm;
	}
	
	@Test
	public void fakeTestInitialize(){
		cachedInstance = this;
	}
	
	public static SpringTestPersistanceFactory getInstance(){
		return cachedInstance;
	}
	
}
