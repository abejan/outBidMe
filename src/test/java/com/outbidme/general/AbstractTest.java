package com.outbidme.general;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

import com.guava.eventbus.adapter.EventBusAdapter;
import com.outbidme.configuration.SystemConfiguration;
import com.outbidme.configuration.SystemConfiguration.Type;
import com.outbidme.model.authentication.Account;
import com.outbidme.model.product.Product;
import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.authentication.AccountDAO;
import com.outbidme.persistance.dao.product.ProductDAO;
import com.outbidme_default_impls.persistance.DefaultPersistanceFactory;

/**
 * Base class for all tests in the project, through which Mockito initializations and other system configurations are done.
 * Make sure to call {@link #registerSystemMocks(Map)}  and setup the OutBidMe component configurations for the test environment, 
 * before running any child class test. 
 */
public abstract class AbstractTest {

	
	protected static PersistanceFactory persistanceFactory;
    protected static PersistanceManager persistanceManager;
    private static boolean isSetup = false;
    private static boolean configsRegistered = false;
    {
      setup();
    }
    
    
    @Before
    public void initMockito(){
        MockitoAnnotations.initMocks(this);
        
    }
    

    public static void setup(){
        if (isSetup) {
            return;
        }
        isSetup = true;
        
        if(!configsRegistered){
           /* if a test has been started and a system configuration has not been provided at this point, 
           use the default mock config. */	
           registerSystemMocks(getDefaultMockSystemConfiguration());
        }
        persistanceFactory = SystemConfiguration.Instance.getPersistanceFactoryComponent();
        persistanceManager = persistanceFactory.getPersistanceManager();
        
        saveAccount(TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD);
        saveAccount(TestUtils.TEST_USERNAME_2, TestUtils.TEST_PASSWORD_2);
        saveProduct(TestUtils.getDefaultProductInstance());
        saveProduct(TestUtils.getExpiredProductInstance());

    }

	/**
	 * This method needs to be called before running any test derived from this class.
	 * This is because all child class tests will use the system configuration provided statically here.
	 * 
	 * Note : The config can only be set once during the run of a test suite to avoid complications of context changes.
	 */
	public static void registerSystemMocks(Map<Type, Object> systemConfiguration) {
		if(!configsRegistered){
		   SystemConfiguration.Instance.setConfiguration(systemConfiguration);
		   configsRegistered = true;
		}
	}
    
	/**
	 * This is the default configuration that can be used to test OutBidMe.
	 * Everything in this config is does not use any frameworks.
	 */
	public static Map<Type, Object> getDefaultMockSystemConfiguration(){
		Map<Type, Object> defaultMockConfiguration = new HashMap<Type, Object>(2);
		defaultMockConfiguration.put(Type.PersistanceFactory, new DefaultPersistanceFactory());
		defaultMockConfiguration.put(Type.EventBus, new EventBusAdapter());
	    return defaultMockConfiguration;
	}
	
	private static void saveProduct(Product product) {
	    ProductDAO productGateway = persistanceFactory.getProductDataAccessObj();
        try {
            productGateway.persist(product);
        } catch (PersistenceException e) {
            throw new AssertionError(e.getMessage());
        }
        assertEquals(true, persistanceManager.contains(product));
	}

    protected static void saveAccount(String username, String password){
        AccountDAO accountGateway = persistanceFactory.getAccountDataAccessObj();
        Account account = new Account(accountGateway.getNextValidId(), username, password);
        try {
            accountGateway.persist(account);
        } catch (PersistenceException e) {
            throw new AssertionError();
        }
        assertEquals(true, persistanceManager.contains(account));
    }

    public static PersistanceManager getPersistanceManager() {
        return persistanceManager;
    }
}
