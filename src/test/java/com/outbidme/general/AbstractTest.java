package com.outbidme.general;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;

import com.outbidme.configuration.SystemConfiguration;
import com.outbidme.configuration.SystemConfiguration.Type;
import com.outbidme.model.authentication.Account;
import com.outbidme.persistance.PersistanceFactory;
import com.outbidme.persistance.PersistanceManager;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.authentication.AccountGateway;
import com.spring.persistance.InMemPersistanceManager;

/**
 * Created by anita on 5/13/2014.
 */
public class AbstractTest {
    private static PersistanceManager persistanceManager;
    private static boolean isSetup = false;

    @BeforeClass
    public static void setup(){
        if (isSetup) {
            return;
        }
        isSetup = true;
        
        registerSystemMocks();
        
        persistanceManager = PersistanceFactory.getPersistanceManager();
        saveAccount(TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD); //this is an implicit test of persisting an account
    }

	private static void registerSystemMocks() {
		SystemConfiguration.Instance.registerComponent(Type.Persistance, InMemPersistanceManager.Instance);
	}

    protected static void saveAccount(String username, String password){
        AccountGateway accountGateway = PersistanceFactory.getAccountGateway();
        Account account = new Account(username, password);
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
