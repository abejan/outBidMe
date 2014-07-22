package com.outbidme.service;

import com.outbidme.configuration.SystemConfiguration;
import com.outbidme.configuration.eventbus.IEventBus;
import com.outbidme.persistance.PersistanceFactory;

/**
 * Base class for all OutBidMe services with commonly used resources.
 */
public class OutBidMeService {

	protected final PersistanceFactory persistanceFactory = SystemConfiguration.Instance.getPersistanceFactoryComponent();
	protected final IEventBus eventBus = SystemConfiguration.Instance.getEventBus();
	
}
