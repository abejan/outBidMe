package com.outbidme.persistance;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import com.outbidme.authentication.Account;
import com.outbidme.general.TestUtils;
import com.outbidme.persistance.authentication.AccountPersistor;

public class PersistanceTest {

	private PersistanceManager pm;


	@BeforeClass
	private void setup(){
		pm = PersistanceFactory.getPersistanceManager();
	}
	
	
	@Test
	public void can_save_an_account(){
		AccountPersistor accountPersistor = PersistanceFactory.getAccountPersistor();
		Account account = TestUtils.getDefaultAccountInstance();
		accountPersistor.persist(account);
		assertEquals(true, pm.contains(account));
		
	}
	
	
	@Test
	public void can_log_in_account_with_username_and_password(){
		
	}
	
}
