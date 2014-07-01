package com.outbidme.presentation.authentication;

import com.outbidme.service.AuthenticationService;

public class LoginPresenter {

	private ILoginView loginView;
    private AuthenticationService authenticationService = new AuthenticationService();

	public LoginPresenter(ILoginView loginView) {
		this.loginView = loginView;
	}

	public void loginAction(String userName, String password) {
		boolean isSuccessful = authenticationService.login(userName, password);
		loginView.setDisplayMessage(isSuccessful ? AuthenticationMessages.LOGIN_SUCCESS.getMessage()
												: AuthenticationMessages.LOGIN_FAIL.getMessage());
	}

    public void logoutAction(String userName){
        boolean isSuccessful = authenticationService.logout(userName);
        loginView.setDisplayMessage(isSuccessful ? AuthenticationMessages.LOGOUT_SUCCESS.getMessage()
                : AuthenticationMessages.LOGOUT_FAIL.getMessage());
    }

}
