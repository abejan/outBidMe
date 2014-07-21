package com.outbidme.persistance.dao.impl.notifications;

import java.util.Collection;

import com.outbidme.model.notifications.MailBox;
import com.outbidme.persistance.EntityMatcher;
import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.impl.AbstractEntityDAO;
import com.outbidme.persistance.dao.notifications.MailBoxDAO;

public class MailBoxDAOImpl extends AbstractEntityDAO implements MailBoxDAO {

	public MailBoxDAOImpl(PersistanceManager persistanceManager) {
		super(persistanceManager);
	}
	
	@Override
	public MailBox findMailBoxForAccount(final int accountId) {
		Collection<MailBox> results = getPersistanceManager().findEntities(new EntityMatcher<MailBox>() {
			@Override
			public boolean matches(MailBox entity) {
				return entity.getAccountId() == accountId;
			}
		}, MailBox.class);
		return getFirstElement(results);
	}

	@Override
	public void persist(MailBox mb) throws PersistenceException {
		 getPersistanceManager().persist(mb);
	}
	
}
