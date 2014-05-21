package com.outbidme.persistance;

public class PersistanceFactoryMock {

	  public static PersistanceManager getPersistanceManager() {
	        return InMemPersistanceManager.Instance;
	    }
	
}
