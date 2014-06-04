package com.spring.controllers.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.outbidme.presentation.authentication.AuthenticationMessages;
import com.outbidme.presentation.authentication.ILoginView;
import com.outbidme.presentation.authentication.LoginPresenter;

@Controller
@RequestMapping("/sign-in")
@SessionAttributes("isAuthenticated")
public class AuthenticationController implements ILoginView{

	private String resultMessage;
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginStart(){
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submitCredentials(String username, String password){
		LoginPresenter loginPresenter = new LoginPresenter(this);
		loginPresenter.loginAction(username, password);
		
		ModelAndView mav = new ModelAndView("login_result");
		mav.addObject("resultMessage", resultMessage);
		mav.addObject("isAuthenticated" ,resultMessage.equals(AuthenticationMessages.SUCCESS.getMessage()) ? 
									Boolean.TRUE : Boolean.FALSE);
		mav.addObject("homePageLink", "home");
		
		return mav;
	}
	
	
	public void setDisplayMessage(String message) {
		  this.resultMessage = message;
	}
	
}
