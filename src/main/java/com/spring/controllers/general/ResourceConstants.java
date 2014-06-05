package com.spring.controllers.general;

/**
 * Stores any resource related attributes like paths relative to the context application root, 
 * or concrete file names.
 */
public final class ResourceConstants {
	
	public static final String HOMEPAGE_VIEW  = "home";
	public static final String HOMEPAGE_URL   = "/home";
	public static final String HOMEPAGE_HREF   = HOMEPAGE_VIEW;
	public static final String HOMEPAGE_FILE  = "home.jsp";
	
	public static final String LOGINPAGE_VIEW = "login";
	public static final String LOGINPAGE_URL  = "/sign-in";
    public static final String LOGOUTPAGE_VIEW = "logout";
	public static final String LOGINPAGE_HREF  = "sign-in";
	public static final String LOGINPAGE_FILE = "login.jsp";
	
	public static final String LOGIN_RESULT_PAGE_VIEW = "login_result";
	public static final String LOGIN_RESULT_PAGE_URL  = "/login_result.jsp";
	public static final String LOGIN_RESULT_PAGE_FILE = "login_result.jsp";
	
}
