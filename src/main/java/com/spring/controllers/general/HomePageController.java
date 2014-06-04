package com.spring.controllers.general;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(ResourceConstants.HOMEPAGE_URL)
public class HomePageController {

	@RequestMapping(method = RequestMethod.GET)
	private ModelAndView homePage(){
		ModelAndView mav = new ModelAndView(ResourceConstants.HOMEPAGE_VIEW);
		mav.addObject("loginPageLink", JSPUtils.buildHREF(ResourceConstants.LOGINPAGE_HREF, "Click to sign in"));
		return mav;
	}

}
