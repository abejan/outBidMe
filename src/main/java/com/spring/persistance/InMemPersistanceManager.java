package com.spring.persistance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.outbidme.model.authentication.Account;
import com.outbidme.persistance.EntityMatcher;
import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.PersistenceException;


/**
 * In memory database mock.
 */
public enum InMemPersistanceManager implements PersistanceManager {

	Instance;
	
	@SuppressWarnings("rawtypes")
	private  Map<Class,  Set<Object>> inMemoryDB = new HashMap<Class, Set<Object>>();
	
	{
		storeDefaults();
	}

	
	
	public boolean contains(Object entity) {
		Set<Object> entityTable = inMemoryDB.get(entity.getClass());
		if(entityTable != null)
		   return entityTable.contains(entity);
	  return false;
	}

	public void persist(Object entity) throws PersistenceException{
		Set<Object> entityTable = inMemoryDB.get(entity.getClass());
		if(entityTable == null){
		   entityTable = new HashSet<Object>();
		   inMemoryDB.put(entity.getClass(), entityTable);
		}
        if (entityTable.contains(entity)){
            throw new PersistenceException();
        }
		entityTable.add(entity);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T findEntity(EntityMatcher<T> matcher, Class<T> clazz) {
		Set<Object> entityTable = inMemoryDB.get(clazz);
		if(entityTable != null){
			for(Object entity : entityTable){
				if(matcher.matches((T) entity)){
				   return (T) entity;
				}
			}
		}
		return null;
	}

	public <T> void removeEntity(EntityMatcher<T> matcher, Class<T> clazz) {
		Set<Object> entityTable = inMemoryDB.get(clazz);
		if(entityTable != null){
			Iterator<T> it = (Iterator<T>) entityTable.iterator();
			while(it.hasNext()){
				T entity = it.next();
				if(matcher.matches((T) entity)){
				   it.remove();
				   return;
				}
			}
		}
	}

    public <T> double getEntityCount(Class<T> clazz){
        return 0;
    }

	private void storeDefaults() {
		//Default Admin account
		inMemoryDB.put(Account.class, new HashSet<Object>(){{
			add(new Account("admin", "123"));
		}});
	}	 

}
