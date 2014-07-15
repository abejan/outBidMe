package com.spring.authentication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;

import com.outbidme.general.TestUtils;
import com.spring.controllers.authentication.AuthenticationController;
import com.spring.controllers.authentication.Credentials;
import com.spring.controllers.general.ResourceConstants;
import com.spring.general.AbstractControllerTest;

public class AuthenticationControllerTest extends AbstractControllerTest {

	@InjectMocks
	private AuthenticationController controller = new AuthenticationController();
	
	@Test
	public void can_return_authentication_response() throws Exception{
		Credentials credentials = new Credentials();
		credentials.setUsername(TestUtils.TEST_USERNAME);
		credentials.setPassword(TestUtils.TEST_PASSWORD);
		
		mockMvc.perform(post(ResourceConstants.LOGINPAGE_URL).content(objectToJson(credentials)).contentType(MediaType.APPLICATION_JSON)).andDo(print());
	}
	

	@Override
	protected Object getController() {
		return controller;
	}
	
}
