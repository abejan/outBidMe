package com.spring.controllers.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.outbidme.presentation.authentication.AuthenticationMessages;
import com.outbidme.presentation.authentication.ILoginView;
import com.outbidme.presentation.authentication.LoginPresenter;
import com.outbidme.util.StringConstants;
import com.spring.controllers.general.ResourceConstants;

@Controller
@SessionAttributes("isAuthenticated")
public class AuthenticationController implements ILoginView{

	private String resultMessage;
    private LoginPresenter loginPresenter = new LoginPresenter(this);

	@RequestMapping(value = ResourceConstants.LOGINPAGE_URL, method = RequestMethod.POST)
	public @ResponseBody AuthResponse submitCredentials(@RequestBody Credentials credentials){

		loginPresenter.loginAction(credentials.getUsername(), credentials.getPassword());

        boolean isAuthenticated = resultMessage.equals(AuthenticationMessages.LOGIN_SUCCESS.getMessage()) ?
        						  Boolean.TRUE : Boolean.FALSE;
        String message = isAuthenticated ? "Welcome, " + credentials.getUsername() : StringConstants.EMPTY;
        
        return new AuthResponse(isAuthenticated, message);
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
