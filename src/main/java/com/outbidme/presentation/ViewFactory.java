package com.outbidme.presentation;

import com.outbidme.presentation.authentication.ILoginView;

public class ViewFactory {

	public static ILoginView getLoginView() {
		return new ILoginView() {
			public void setDisplayMessage(String message) {
				System.out.println(message);				
			}
		};
	}
	
}
