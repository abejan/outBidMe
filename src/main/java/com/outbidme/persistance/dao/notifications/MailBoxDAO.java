package com.outbidme.persistance.dao.notifications;

import com.outbidme.model.notifications.MailBox;
import com.outbidme.persistance.PersistenceException;

public interface MailBoxDAO {

	public MailBox findMailBoxForAccount(int accountId);

	public void persist(MailBox mb) throws PersistenceException;

}
