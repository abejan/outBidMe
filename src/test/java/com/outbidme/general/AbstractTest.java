package com.outbidme.general;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.MockitoAnnotations;

import com.outbidme.configuration.SystemConfiguration;
import com.outbidme.configuration.SystemConfiguration.Type;
import com.outbidme.model.authentication.Account;
import com.outbidme.model.product.Product;
import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.authentication.AccountGateway;
import com.outbidme.persistance.product.ProductGateway;
import com.spring.persistance.InMemPersistanceManager;

/**
 * Created by anita on 5/13/2014.
 */
public class AbstractTest {
    private static PersistanceManager persistanceManager;
    private static boolean isSetup = false;

    @Before
    public void initMockito(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void setup(){
        if (isSetup) {
            return;
        }
        isSetup = true;
        
        registerSystemMocks();
        
        persistanceManager = PersistanceFactory.getPersistanceManager();
        saveAccount(TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD);
        saveAccount(TestUtils.TEST_USERNAME_2, TestUtils.TEST_PASSWORD_2);
        saveProduct(TestUtils.getDefaultProductInstance());
        saveProduct(TestUtils.getExpiredProductInstance());

    }

	private static void saveProduct(Product product) {
	    ProductGateway productGateway = PersistanceFactory.getProductGateway();
        try {
            productGateway.persist(product);
        } catch (PersistenceException e) {
            throw new AssertionError(e.getMessage());
        }
        assertEquals(true, persistanceManager.contains(product));
	}

	private static void registerSystemMocks() {
		SystemConfiguration.Instance.registerComponent(Type.Persistance, InMemPersistanceManager.Instance);
	}

    protected static void saveAccount(String username, String password){
        AccountGateway accountGateway = PersistanceFactory.getAccountGateway();
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
