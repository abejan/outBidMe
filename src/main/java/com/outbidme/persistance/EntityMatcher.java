package com.outbidme.persistance;

public interface EntityMatcher<T> {

	public boolean matches(T entity);
	
}
