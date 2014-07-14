package com.spring.controllers.general;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.spring.controllers.general.ResourceConstants;

@Controller
@RequestMapping(ResourceConstants.HOMEPAGE_URL)
public class HomePageController {

	private static final Logger logger = Logger.getLogger(HomePageController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	private ModelAndView homePage(){
		
		if(logger.isDebugEnabled()){
			logger.debug("Reponse to GET is executed");
		}
		
		ModelAndView mav = new ModelAndView(ResourceConstants.HOMEPAGE_VIEW);
		mav.addObject("loginPageLink", ResourceConstants.LOGINPAGE_HREF);
		return mav;
	}

}
