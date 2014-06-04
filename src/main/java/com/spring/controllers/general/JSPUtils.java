package com.spring.controllers.general;

/**
 * Offers utility methods for building JSP or HTML tags..
 */
public final class JSPUtils {

	private JSPUtils(){}
	
	public static String buildHREF(String url, String message) {
		return "<a href = \"" + url + "\">" + message + "</a>";
	}
}
