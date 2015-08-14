package util;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {
	
	private static int MIN_AGE = 0;
	
	public static int SECONDS_PER_YEAR = 60*60*24*365;
	
	public static void setCookie(String name, String value, int expiry) {

		Cookie cookie = null;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();		

		Cookie[] userCookies = request.getCookies();
		
		if (userCookies != null && userCookies.length > 0) {
			
			for (int i = 0; i < userCookies.length; i++) {
				
				if (userCookies[i].getName().equals(name)) {
					cookie = userCookies[i];
					break;
				}
			}
		}

		if (cookie != null) {
			cookie.setValue(value);
		} else {
			cookie = new Cookie(name, value);
			cookie.setPath(request.getContextPath());
		}
		
		cookie.setMaxAge(expiry);

		((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(cookie);
	}

	public Cookie getCookie(HttpServletRequest request, String name) {		
		
		if (request == null) {
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			
			request = (HttpServletRequest) facesContext
					.getExternalContext().getRequest();
		}		

		Cookie[] userCookies = request.getCookies();
		
		if (userCookies != null && userCookies.length > 0) {
			
			for (int i = 0; i < userCookies.length; i++) {
				if (userCookies[i].getName().equals(name)) {
					Cookie cookie = userCookies[i];
					return cookie;
				}
			}
		}

		return null;
	}

	public String getValidCookieValue(String name) {
		
		return getValidCookieValue(null, name);		
	}
	
	public String getValidCookieValue(HttpServletRequest request, String name) {
	    
		String value = null;
		
		Cookie cookie = getCookie(request, name);
		
		// if (cookie != null && cookie.getMaxAge() > MIN_AGE) {
	    if (cookie != null) {
	        value = cookie.getValue();
	    }
	    
	    return value;
	}

	public void eraseCookie() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();

		HttpServletResponse response = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();

		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			
			for (Cookie cookie: cookies) {
				cookie.setValue("");
				cookie.setPath("/");
				cookie.setDomain("/");
				cookie.setMaxAge(-1);
				cookie.setComment("EXPIRING COOKIE at " + System.currentTimeMillis());
				response.addCookie(cookie);
			}
		}
	}
}