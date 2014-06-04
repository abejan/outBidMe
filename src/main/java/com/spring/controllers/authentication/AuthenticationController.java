package com.spring.controllers.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.outbidme.presentation.authentication.AuthenticationMessages;
import com.outbidme.presentation.authentication.ILoginView;
import com.outbidme.presentation.authentication.LoginPresenter;
import com.spring.controllers.general.JSPUtils;
import com.spring.controllers.general.ResourceConstants;

@Controller
@RequestMapping(ResourceConstants.LOGINPAGE_URL)
@SessionAttributes("isAuthenticated")
public class AuthenticationController implements ILoginView{

	private String resultMessage;
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginStart(){
		return ResourceConstants.LOGINPAGE_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submitCredentials(String username, String password){
		LoginPresenter loginPresenter = new LoginPresenter(this);
		loginPresenter.loginAction(username, password);
		
		ModelAndView mav = new ModelAndView(ResourceConstants.LOGIN_RESULT_PAGE_VIEW);
		mav.addObject("resultMessage", resultMessage);
		mav.addObject("isAuthenticated" ,resultMessage.equals(AuthenticationMessages.SUCCESS.getMessage()) ? 
									Boolean.TRUE : Boolean.FALSE);
		mav.addObject("homePageLink", JSPUtils.buildHREF(ResourceConstants.HOMEPAGE_HREF, "Return to home page"));
		mav.addObject("loginPageLink", JSPUtils.buildHREF(ResourceConstants.LOGINPAGE_HREF, "Try again"));
		
		return mav;
	}
	
	
	public void setDisplayMessage(String message) {
		  this.resultMessage = message;
	}
	
}
