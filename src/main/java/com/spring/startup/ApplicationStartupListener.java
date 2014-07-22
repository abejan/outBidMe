package com.spring.startup;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.guava.eventbus.adapter.EventBusAdapter;
import com.outbidme.configuration.SystemConfiguration;
import com.outbidme.configuration.SystemConfiguration.Type;
import com.outbidme_default_impls.persistance.DefaultPersistanceFactory;
import com.spring.persistance.SpringPersistanceFactory;

/**
 * Startup hook for application initializations (i.e. providing a PersistanceManager implementation)
 */
public class ApplicationStartupListener extends ContextLoaderListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		SystemConfiguration.Instance.registerComponent(Type.PersistanceFactory, new SpringPersistanceFactory());
		SystemConfiguration.Instance.registerComponent(Type.EventBus, new EventBusAdapter());
	}
		
}
