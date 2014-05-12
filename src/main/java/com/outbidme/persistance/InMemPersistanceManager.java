package com.outbidme.persistance;

import java.util.HashSet;
import java.util.Set;

public enum InMemPersistanceManager implements PersistanceManager{

	Instance;
	
	
	private Set<Object> inMemoryDB = new HashSet<Object>();

	public boolean contains(Object entity) {
		return inMemoryDB.contains(entity);
	}

	public void persist(Object entity) {
		inMemoryDB.add(entity);
	}
	 
}
