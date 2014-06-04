package com.outbidme.presentation;

import com.outbidme.general.AbstractTest;
import com.outbidme.presentation.authentication.AuthenticationMessages;
import org.junit.Before;
import org.junit.Test;

import com.outbidme.general.TestUtils;
import com.outbidme.presentation.authentication.ILoginView;
import com.outbidme.presentation.authentication.LoginPresenter;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class LoginInterfaceTest extends AbstractTest {

    @Mock
    ILoginView loginView;

	@Test
	public void can_login_through_user_interface(){
		LoginPresenter presenter = new LoginPresenter(loginView);
		presenter.loginAction(TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD);
        verify(loginView).setDisplayMessage( AuthenticationMessages.SUCCESS.getMessage());
	}
}
