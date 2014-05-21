package com.outbidme.presentation;

import org.junit.Test;

import com.outbidme.general.TestUtils;
import com.outbidme.presentation.authentication.ILoginView;
import com.outbidme.presentation.authentication.LoginPresenter;

public class LoginInterfaceTest {

	@Test
	public void can_login_through_user_interface(){
		
		ILoginView loginView = ViewFactory.getLoginView();
		LoginPresenter presenter = new LoginPresenter(loginView);
		presenter.loginAction(TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD);
		//TODO : Use mockito to complete this test
		//assertTrue(loginView.getDisplayMessage().equals(AuthenticationMessages.SUCCESS));
	}
}
