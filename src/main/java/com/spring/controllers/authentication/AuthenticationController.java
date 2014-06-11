package com.spring.controllers.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.outbidme.presentation.authentication.AuthenticationMessages;
import com.outbidme.presentation.authentication.ILoginView;
import com.outbidme.presentation.authentication.LoginPresenter;
import com.spring.controllers.general.ResourceConstants;

@Controller
@SessionAttributes("isAuthenticated")
public class AuthenticationController implements ILoginView{

	private String resultMessage;
    private LoginPresenter loginPresenter = new LoginPresenter(this);
	
	@RequestMapping(value = ResourceConstants.LOGINPAGE_URL, method = RequestMethod.GET)
	public String loginStart(){
		return ResourceConstants.LOGINPAGE_VIEW;
	}

	@RequestMapping(value = ResourceConstants.LOGINPAGE_URL, method = RequestMethod.POST)
	public ModelAndView submitCredentials(String username, String password){

		loginPresenter.loginAction(username, password);
		

        boolean isAuthenticated = resultMessage.equals(AuthenticationMessages.LOGIN_SUCCESS.getMessage()) ?
                Boolean.TRUE : Boolean.FALSE;
        if (isAuthenticated) {
            ModelAndView mav = new ModelAndView(ResourceConstants.LOGIN_RESULT_PAGE_VIEW);
            mav.addObject("resultMessage", resultMessage);
            mav.addObject("userName", username);
            mav.addObject("homePageLink", ResourceConstants.HOMEPAGE_HREF);
            mav.addObject("logoutLink", ResourceConstants.LOGOUTPAGE_HREF);
            return mav;
        } else {
            return new ModelAndView(ResourceConstants.HOMEPAGE_VIEW);
        }
	}

    @RequestMapping(value = ResourceConstants.LOGOUTPAGE_URL, method = RequestMethod.GET)
    public ModelAndView logout(@RequestParam String username){
        loginPresenter.logoutAction(username);
        boolean loggedOut = resultMessage.equals(AuthenticationMessages.LOGOUT_SUCCESS.getMessage()) ?
                Boolean.TRUE : Boolean.FALSE;
        if (loggedOut) {
            ModelAndView mav = new ModelAndView(ResourceConstants.HOMEPAGE_VIEW);
            mav.addObject("loginPageLink", ResourceConstants.LOGINPAGE_HREF);
            return mav;
        } else {
            return new ModelAndView(ResourceConstants.LOGIN_RESULT_PAGE_VIEW);
        }
    }

    public void setDisplayMessage(String message) {
		  this.resultMessage = message;
	}
	
}
