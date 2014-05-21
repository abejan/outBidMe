package com.outbidme.general;

import com.outbidme.model.authentication.Account;

public class TestUtils {

	public static final String TEST_USERNAME = "john";
	public static final String TEST_PASSWORD = "123";

	
	public static Account getDefaultAccountInstance(){
		return new Account(TEST_USERNAME, TEST_PASSWORD);
	}
	
}
