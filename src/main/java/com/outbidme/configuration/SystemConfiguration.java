package com.outbidme.configuration;

import java.util.HashMap;
import java.util.Map;

import com.outbidme.configuration.eventbus.IEventBus;
import com.outbidme.persistance.PersistanceFactory;



/**
 * This is the place where services and components used by OutBidMe can be registered / plugged-in.
 */
public enum SystemConfiguration {
	
	Instance;

	public static enum Type{
		PersistanceFactory,
        EventBus
	}
	
	private Map<Type, Object> componentRegistry = new HashMap<Type, Object>(2);
	
	public void registerComponent(Type compType, Object component){
		assert (compType != null && component != null) : "Component can not be null";
		componentRegistry.put(compType, component);
	}
	
	public Object getComponent(Type compType){
		Object component = componentRegistry.get(compType);
		assert (component != null) : "Expected system component not registered";
		return component;
	}
	
	 
	/*------------  Convenience methods below ---------------*/
	
	public PersistanceFactory getPersistanceFactoryComponent(){
		return (PersistanceFactory) getComponent(Type.PersistanceFactory);
	}
	
	public IEventBus getEventBus(){
	    return (IEventBus) getComponent(SystemConfiguration.Type.EventBus);
	}
}
