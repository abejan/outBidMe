package com.outbidme.service;

import com.outbidme.configuration.eventbus.IEventListener;
import com.outbidme.model.authentication.Account;
import com.outbidme.model.notifications.BidMessage;
import com.outbidme.model.notifications.MailBox;
import com.outbidme.model.notifications.Message;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.authentication.AccountDAO;
import com.outbidme.persistance.dao.notifications.MailBoxDAO;

/**
 * Performs messaging services as a result of user or system actions 
 * (i.e. bid win notifications, outbidding messages, user to user messaging etc.).
 */
public class NotificationsService extends OutBidMeService implements IEventListener{

	
    public NotificationsService() {
        eventBus.register(this);
    }

    public MailBox getAccountMailBox(int accountId) {
		Account account = retrieveAccount(accountId);
		if(account != null){
		   MailBoxDAO mbGateway = persistanceFactory.getMailBoxDataAccessObj();
		   MailBox mb = mbGateway.findMailBoxForAccount(account.getId());
		   if(mb == null){
			 //MailBox does not exist; create mail for requested account
			 mb =  createAndPersistMailBox(account.getId(), mbGateway);
		   }
		   return mb;
		}
	  return null;
	}

	public void sendMail(int accountId, Message message) {
		MailBox mailBox = getAccountMailBox(accountId);
		mailBox.addMessage(message);
	}
	
	private MailBox createAndPersistMailBox(int accountId, MailBoxDAO mbGateway) {
		  MailBox mb = new MailBox(accountId);
		  try {
			mbGateway.persist(mb);
		  } catch (PersistenceException e) {
			  return null;
		  }
		return mb;
	}

	private Account retrieveAccount(int accountId) {
		  AccountDAO accountGateway = persistanceFactory.getAccountDataAccessObj();
		  Account account = accountGateway.findAccountById(accountId);
		return account;
	}

    @Override
    public void handle(Object event) {
        final BidMessage bidMessage = (BidMessage) event;
        sendMail(bidMessage.getAccountId(), bidMessage);
    }
}
