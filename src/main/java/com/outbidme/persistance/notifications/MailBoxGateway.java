package com.outbidme.persistance.notifications;

import com.outbidme.model.notifications.MailBox;
import com.outbidme.persistance.PersistenceException;

public interface MailBoxGateway {

	public MailBox findMailBoxForAccount(int accountId);

	public void persist(MailBox mb) throws PersistenceException;

}
