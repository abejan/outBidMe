package com.spring.controllers.general;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomePageController {

	@RequestMapping(method = RequestMethod.GET)
	private ModelAndView homePage(){
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("loginPageLink", "<a href = \" login.jsp \" >Click to sign in</a>");
		return mav;
	}
	
	
}
