package com.spring.persistance.dao.notifications;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.outbidme.model.notifications.MailBox;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.notifications.MailBoxDAO;

public class MailBoxSpringDAO implements MailBoxDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public MailBox findMailBoxForAccount(int accountId) {
		return em.find(MailBox.class, accountId);
	}

	@Override
	public void persist(MailBox mb) throws PersistenceException {
		em.persist(mb);
	}

}
