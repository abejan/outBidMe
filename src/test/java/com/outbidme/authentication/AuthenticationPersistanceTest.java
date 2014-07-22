package com.outbidme.authentication;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.outbidme.general.AbstractTest;
import com.outbidme.general.TestUtils;
import com.outbidme.model.authentication.Account;
import com.outbidme.persistance.PersistenceException;
import com.outbidme.persistance.dao.authentication.AccountDAO;

public class AuthenticationPersistanceTest extends AbstractTest{
	
    private static final AccountDAO accountGateway = persistanceFactory.getAccountDataAccessObj();

	@Test
	public void can_retrieve_an_existing_account_with_username(){
		Account account = accountGateway.findAccountByUserName(TestUtils.TEST_USERNAME);
		assertEquals(true, account != null);
		assertEquals(true, account.getUsername().equals(TestUtils.TEST_USERNAME));
	}

	@Test
	public void can_retrieve_an_existing_account_with_Id(){
		Account account = accountGateway.findAccountById(TestUtils.TEST_ACCOUNT_ID);
		assertEquals(true, account != null);
		assertEquals(true, account.getUsername().equals(TestUtils.TEST_USERNAME));
	}
	
    @Test
    public void can_not_add_same_account_twice(){
        Account account1 = new Account(accountGateway.getNextValidId(), "aaa", "111");
        Account account2 = new Account(accountGateway.getNextValidId(), "aaa", "111");
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
    public void can_remove_account_by_username(){
    	 String username = "test_remove";
    	 Account account = new Account(accountGateway.getNextValidId(), username, "111");
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
