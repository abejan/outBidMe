package com.outbidme.persistance;

/**
 * Gateway to the storage layer.
 */
public interface  PersistanceManager {

	public void persist(Object entity) throws PersistenceException;
	
	public boolean contains(Object entity);

	public <T> T findEntity(EntityMatcher<T> matcher, Class<T> clazz);

	
}
