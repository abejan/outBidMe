package com.outbidme.presentation;

import com.outbidme.presentation.authentication.AuthenticationMessages;
import com.outbidme.presentation.authentication.ILoginView;

public class ViewFactory {

	public static ILoginView getLoginView() {
		return new ILoginView() {
			public void setDisplayMessage(AuthenticationMessages message) {
				System.out.println(message.getMessage());
			}
			
		};
	}

}
