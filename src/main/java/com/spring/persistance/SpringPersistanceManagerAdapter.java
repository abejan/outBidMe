package com.spring.persistance;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.outbidme.persistance.EntityMatcher;
import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.PersistenceException;

/**
 * Bridge between Spring/JPA and internal OutBidMe {@link PersistanceManager} component.
 */
public class SpringPersistanceManagerAdapter implements PersistanceManager{

	@PersistenceContext
	private EntityManager em;

	@Override
	public void persist(Object entity) throws PersistenceException {
		em.persist(entity);
	}

	@Override
	public boolean contains(Object entity) {
		return em.contains(entity);
	}

	@Override
	public <T> Collection<T> findEntities(EntityMatcher<T> matcher, Class<T> clazz) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> void removeEntity(EntityMatcher<T> matcher, Class<T> clazz) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> int getEntityCount(Class<T> clazz) {
		Query query = em.createQuery("SELECT entity FROM " + clazz.getName() + "entity");
		return (Integer)query.getResultList().size();
	}
	
}
