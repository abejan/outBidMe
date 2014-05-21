package com.outbidme.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.outbidme.general.AbstractTest;
import org.junit.Test;

import com.outbidme.general.TestUtils;
import com.outbidme.model.authentication.Account;
import com.outbidme.model.authentication.AuthenticationService;


public class AuthenticationTest extends AbstractTest{

    private static AuthenticationService authenticationService = new AuthenticationService();

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

    @Test
    public void can_login(){
        assertTrue(authenticationService.login(TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD));
    }

    @Test
    public void is_user_logged_in_after_successful_login(){
        authenticationService.login(TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD);
        assertTrue(authenticationService.isLoggedIn(TestUtils.TEST_USERNAME));
    }

    @Test
    public void can_login_twice(){
        final String username = "aaaa";
        final String password = "123456";
        saveAccount(username, password);

        boolean firstLogin = authenticationService.login(username, password);
        boolean secondLogin = authenticationService.login(username, password);

        assertTrue(firstLogin);
        assertFalse(secondLogin);
    }

    @Test
    public void cannot_login_if_account_was_not_saved(){
        final String username = "invalid";
        final String password = "123456";

        assertFalse(authenticationService.login(username, password));
    }

    @Test
    public void can_log_out(){
        authenticationService.login(TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD);
        assertTrue(authenticationService.logout(TestUtils.TEST_USERNAME));
    }

    @Test
    public void is_logged_in_after_logout(){
        authenticationService.login(TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD);
        assertTrue(authenticationService.logout(TestUtils.TEST_USERNAME));
        assertFalse(authenticationService.isLoggedIn(TestUtils.TEST_USERNAME));
    }
    
    @Test
    public void can_receive_user_confirmation_after_succesful_login(){
    	
    }
    
    
    
}
