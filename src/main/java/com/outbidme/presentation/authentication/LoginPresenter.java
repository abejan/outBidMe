package com.outbidme.presentation.authentication;

import com.outbidme.model.authentication.AuthenticationService;

public class LoginPresenter {

	private ILoginView loginView;

	public LoginPresenter(ILoginView loginView) {
		this.loginView = loginView;
	}

	public void loginAction(String userName, String password) {
		AuthenticationService authenticationService = new AuthenticationService();
		boolean isSuccesful = authenticationService.login(userName, password);
		loginView.setDisplayMessage(isSuccesful ? AuthenticationMessages.SUCCESS.getMessage()
												: AuthenticationMessages.FAIL.getMessage());
	}

}
