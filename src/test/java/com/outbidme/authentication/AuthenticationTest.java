package com.outbidme.authentication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.outbidme.general.TestUtils;


public class AuthenticationTest{

	
	@Test
	public void can_create_account_with_username_and_password() {
		Account account = TestUtils.getDefaultAccountInstance();
		assertEquals(true, account.getUsername().equals(TestUtils.TEST_USERNAME));
		assertEquals(true, account.getPassword().equals(TestUtils.TEST_PASSWORD));
	}
	
	
	@Test
	public void can_change_account_password(){
		Account account = TestUtils.getDefaultAccountInstance();
		assertEquals(true, account.getPassword().equals(TestUtils.TEST_PASSWORD));
		account.setPassword("asd");
		assertEquals(true, account.getPassword().equals("asd"));
	}
}
