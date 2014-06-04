package com.spring.startup;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.outbidme.configuration.SystemConfiguration;
import com.outbidme.configuration.SystemConfiguration.Type;
import com.spring.persistance.InMemPersistanceManager;

/**
 * Startup hook for application initializations (i.e. providing a PersistanceManager implementation)
 */
public class ApplicationStartupListener extends ContextLoaderListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		SystemConfiguration.Instance
			.registerComponent(Type.Persistance, InMemPersistanceManager.Instance);
	}
		
}
