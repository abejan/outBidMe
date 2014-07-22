package com.spring.persistance;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.dao.authentication.AccountDAO;
import com.outbidme.persistance.dao.notifications.MailBoxDAO;
import com.outbidme.persistance.dao.product.ProductDAO;
import com.outbidme.persistance.dao.product.UserBidDAO;


@Service
@Transactional
public class SpringPersistanceFactory implements PersistanceFactory {

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
