package com.spring.general;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ContextConfiguration;

import com.guava.eventbus.adapter.EventBusAdapter;
import com.outbidme.configuration.SystemConfiguration.Type;
import com.outbidme.general.AbstractTest;
import com.outbidme.general.OutBidMeTestSuite;
import com.spring.authentication.AuthenticationControllerTest;
import com.spring.authentication.AuthenticationSpringDAOTest;


//specify a runner class: Suite.class
@RunWith(Suite.class)

//specify an array of test classes
@Suite.SuiteClasses({
	OutBidMeTestSuite.class, 
	AuthenticationControllerTest.class, 
	AuthenticationSpringDAOTest.class
})
@ContextConfiguration( "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class AllTestsSuite {
	
	@BeforeClass
	public static void setupSuite(){
		
		Map<Type, Object> springContextMockConfiguration = new HashMap<Type, Object>(2);
		springContextMockConfiguration.put(Type.PersistanceFactory, new SpringTestPersistanceFactory());
		springContextMockConfiguration.put(Type.EventBus, new EventBusAdapter());
		
		AbstractTest.registerSystemMocks(springContextMockConfiguration);
	}
	
}
