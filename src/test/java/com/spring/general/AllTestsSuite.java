package com.spring.general;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestSuite;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runners.model.InitializationError;

import com.guava.eventbus.adapter.EventBusAdapter;
import com.outbidme.authentication.AuthenticationPersistanceTest;
import com.outbidme.configuration.SystemConfiguration.Type;
import com.outbidme.general.AbstractTest;
import com.outbidme.general.OutBidMeTestSuite;
import com.spring.authentication.AuthenticationControllerTest;


public class AllTestsSuite {
	
	
	
	public static void initFakeSpringTestsForAutowiring(){
		JUnitCore.runClasses(SpringTestPersistanceFactory.class);
	}
	
	public static void setupConfigurations(){
		Map<Type, Object> springContextMockConfiguration = new HashMap<Type, Object>(2);
		springContextMockConfiguration.put(Type.PersistanceFactory, SpringTestPersistanceFactory.getInstance());
		springContextMockConfiguration.put(Type.EventBus, new EventBusAdapter());
		
		AbstractTest.registerSystemMocks(springContextMockConfiguration);
	}

	@Test
	public void runAll() throws InitializationError{
		initFakeSpringTestsForAutowiring();
		setupConfigurations();
		
		TestSuite springTests = new TestSuite();
		for(Class<?> clazz : getSpringTestClasses()){
			springTests.addTest(createTest(clazz));
		}

		JUnitCore jUnitCore = new JUnitCore();
		jUnitCore.run(OutBidMeTestSuite.class);
		
		jUnitCore.run(springTests);
	}

	private static junit.framework.Test createTest(Class<?> clazz) {
		return TestSuite.createTest(clazz, clazz.getName() + " test");
	}
	
	private static Class<?>[] getSpringTestClasses(){
		return new Class<?>[] {AuthenticationPersistanceTest.class, 
							   AuthenticationControllerTest.class};
	}
	
}
