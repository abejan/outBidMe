package com.outbidme.persistance;

import java.sql.Savepoint;

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
	public void can_log_in_existing_account_with_username_and_password(){
		AccountGateway accountGateway = PersistanceFactory.getAccountGateway();
		Account account = accountGateway.findAccountByUserName(TestUtils.TEST_USERNAME);
		assertEquals(true, account != null);
		assertEquals(true, account.getUsername().equals(TestUtils.TEST_USERNAME));
	}

	
	private static void saveDefaultAccount(){
		AccountGateway accountPersistor = PersistanceFactory.getAccountGateway();
		Account account = TestUtils.getDefaultAccountInstance();
		accountPersistor.persist(account);
		assertEquals(true, pm.contains(account));
	}
	
}
