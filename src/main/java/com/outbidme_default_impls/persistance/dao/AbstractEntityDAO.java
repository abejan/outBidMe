package com.outbidme_default_impls.persistance.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.outbidme.persistance.EntityMatcher;
import com.outbidme.persistance.PersistanceManager;

/**
 * Base implementation of an OutBidMe DAO/Gateway.
 */
public class AbstractEntityDAO {

	private final PersistanceManager persistanceManager;

	public AbstractEntityDAO(PersistanceManager persistanceManager) {
		this.persistanceManager = persistanceManager;
	}

	public PersistanceManager getPersistanceManager() {
		return persistanceManager;
	}
	
	protected final <T> List<T> toList(Collection<T> collection) {
		List<T> list = new ArrayList<T>();
		for(T bid : collection)
			list.add(bid);
		return list;
	}
	
	protected final <T> T getFirstElement(Collection<T> collection) {
		 if(!collection.isEmpty())
				return collection.iterator().next();
		 return null;
	}
	
	protected final <T> EntityMatcher<T> getAllEntitiesMatcher(Class<T> forClass){
		return new EntityMatcher<T>() {
			@Override
			public boolean matches(T entity) {
				return true;
			}
		};
	}
	
}
