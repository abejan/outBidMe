package com.outbidme.persistance;


import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import com.outbidme.authentication.Account;
import com.outbidme.general.TestUtils;
import com.outbidme.persistance.authentication.AccountGateway;

public class PersistanceTest {

	private static PersistanceManager pm;


	@BeforeClass
	public static void setup(){
		pm = PersistanceFactory.getPersistanceManager();
		saveDefaultAccount(); //this is an implicit test of persisting an account
	}
	
	
	@Test
	public void can_retrieve_an_existing_account_with_username(){
		AccountGateway accountGateway = PersistanceFactory.getAccountGateway();
		Account account = accountGateway.findAccountByUserName(TestUtils.TEST_USERNAME);
		assertEquals(true, account != null);
		assertEquals(true, account.getUsername().equals(TestUtils.TEST_USERNAME));
	}

	
	private static void saveDefaultAccount(){
		AccountGateway accountGateway = PersistanceFactory.getAccountGateway();
		Account account = TestUtils.getDefaultAccountInstance();
		accountGateway.persist(account);
		assertEquals(true, pm.contains(account));
	}
	
}
