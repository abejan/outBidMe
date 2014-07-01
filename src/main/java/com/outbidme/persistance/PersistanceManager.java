package com.outbidme.persistance;

import java.util.Collection;


/**
 * Gateway to the storage layer.
 */
public interface  PersistanceManager {

	public void persist(Object entity) throws PersistenceException;
	
	public boolean contains(Object entity);

	public <T> Collection<T> findEntities(EntityMatcher<T> matcher, Class<T> clazz);
	
	public <T> void removeEntity(EntityMatcher<T> matcher, Class<T> clazz);

    public <T> int getEntityCount(Class<T> clazz) ;

}
