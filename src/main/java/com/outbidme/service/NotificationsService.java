package com.outbidme.service;

import com.outbidme.model.authentication.Account;
import com.outbidme.model.notifications.MailBox;
import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.authentication.AccountGateway;
import com.outbidme.persistance.notifications.MailBoxGateway;

/**
 * Performs messaging services as a result of user or system actions 
 * (i.e. bid win notifications, outbidding messages, user to user messaging etc.).
 */
public class NotificationsService {
	
	public MailBox getAccountMailBox(String userName) {
		Account account = retrieveAccount(userName);
		if(account != null){
		   MailBoxGateway mbGateway = PersistanceFactory.getMailBoxGateway();
		   MailBox mb = mbGateway.findMailBoxForAccount(account.getId());
		   if(mb == null){
			 //MailBox does not exist; create mail for requested account
			 mb =  createAndPersistMailBox(account.getId(), mbGateway);
		   }
		   return mb;
		}
	  return null;
	}

	private MailBox createAndPersistMailBox(int accountId, MailBoxGateway mbGateway) {
		  MailBox mb = new MailBox(accountId);
		  try {
			mbGateway.persist(mb);
		  } catch (PersistenceException e) {
			  return null;
		  }
		return mb;
	}

	private Account retrieveAccount(String userName) {
		  AccountGateway accountGateway = PersistanceFactory.getAccountGateway();
		  Account account = accountGateway.findAccountByUserName(userName);
		return account;
	}

}