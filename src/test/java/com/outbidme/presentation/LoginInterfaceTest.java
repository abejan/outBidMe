package com.outbidme.presentation;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mock;

import com.outbidme.general.AbstractTest;
import com.outbidme.general.TestUtils;
import com.outbidme.presentation.authentication.AuthenticationMessages;
import com.outbidme.presentation.authentication.ILoginView;
import com.outbidme.presentation.authentication.LoginPresenter;

public class LoginInterfaceTest extends AbstractTest {

    @Mock
    ILoginView loginView;

	@Test
	public void can_login_through_user_interface(){
		LoginPresenter presenter = new LoginPresenter(loginView);
		presenter.loginAction(TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD);
        verify(loginView).setDisplayMessage( AuthenticationMessages.LOGIN_SUCCESS.getMessage());
	}

    @Test
    public void cannot_login_through_user_interface(){
        LoginPresenter presenter = new LoginPresenter(loginView);
        presenter.loginAction("BLA", "BLA");
        verify(loginView).setDisplayMessage(AuthenticationMessages.LOGIN_FAIL.getMessage());
    }

    @Test
    public void can_logout_though_user_interface(){
        LoginPresenter presenter = new LoginPresenter(loginView);
        presenter.loginAction(TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD);
        presenter.logoutAction(TestUtils.TEST_USERNAME);
        verify(loginView).setDisplayMessage( AuthenticationMessages.LOGOUT_SUCCESS.getMessage());
    }

    @Test
    public void cannot_logout_though_user_interface(){
        LoginPresenter presenter = new LoginPresenter(loginView);
        presenter.loginAction(TestUtils.TEST_USERNAME, TestUtils.TEST_PASSWORD);
        presenter.logoutAction(TestUtils.TEST_USERNAME + "  ");
        verify(loginView).setDisplayMessage( AuthenticationMessages.LOGOUT_FAIL.getMessage());
    }
}
