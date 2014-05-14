package com.outbidme.persistance;


import com.outbidme.general.AbstractTest;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.outbidme.authentication.Account;
import com.outbidme.general.TestUtils;
import com.outbidme.persistance.authentication.AccountGateway;

public class PersistanceTest extends AbstractTest{
    private static final AccountGateway accountGateway = PersistanceFactory.getAccountGateway();

	@Test
	public void can_retrieve_an_existing_account_with_username(){
		Account account = accountGateway.findAccountByUserName(TestUtils.TEST_USERNAME);
		assertEquals(true, account != null);
		assertEquals(true, account.getUsername().equals(TestUtils.TEST_USERNAME));
	}

    @Test
    public void can_not_add_same_account_twice(){
        Account account1 = new Account("aaa", "111");
        Account account2 = new Account("aaa", "111");
        boolean secondPersistExc = false;
        try {
            accountGateway.persist(account1);
        } catch (PersistenceException e) {
            fail();
        }
        try {
            accountGateway.persist(account2);
        } catch (PersistenceException e) {
            secondPersistExc = true;
        }
        assertTrue(secondPersistExc);
    }
    
    @Test
    public void can_remove_account_if_admin(){
    	 String username = "aaa";
    	 Account account = new Account(username, "111");
    	 try {
			accountGateway.persist(account);
		} catch (PersistenceException e) {
			fail();
		}
    	 assertTrue(getPersistanceManager().contains(account));
    	 accountGateway.removeAccountWithUsername(username);
    	 assertTrue(!getPersistanceManager().contains(account));
    }
    
}
