package com.outbidme.persistance;

import com.outbidme.model.product.Product;

/**
 * Gateway to the storage layer.
 */
public interface  PersistanceManager {

	public void persist(Object entity) throws PersistenceException;
	
	public boolean contains(Object entity);

	public <T> T findEntity(EntityMatcher<T> matcher, Class<T> clazz);

	public <T> void removeEntity(EntityMatcher<T> matcher, Class<T> clazz);

    public <T> double getEntityCount(Class<T> clazz);
}
