package com.outbidme.configuration.eventbus;

import com.outbidme.configuration.SystemConfiguration;

/**
 * Service which returns the singleton event bus for the entire application.
 */
public class EventBusService {

	public IEventBus getEventBus(){
	    return (IEventBus) SystemConfiguration.Instance.getComponent(SystemConfiguration.Type.EventBus);
	}

}
