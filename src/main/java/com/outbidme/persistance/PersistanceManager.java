package com.outbidme.persistance;



/**
 * Gateway to the storage layer.
 */
public interface PersistanceManager {

	public void persist(Object entity);
	
	public boolean contains(Object entity);

	
}
