package filter;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.CookieHelper;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "/*" })
public class AuthFilter implements Filter {

	private Logger logger = LogManager.getLogger(AuthFilter.class);

	public AuthFilter() {}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			// Check whether session variable is set
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			
			HttpSession ses = req.getSession(false);
			
			// Allow user to proccede if url is login.xhtml or user logged in or
			// User is accessing any page in //public folder
			String reqURI = req.getRequestURI();
			logger.info("URI Requisition: " + reqURI);			

			// Manter o usuário logado via Cookie.
			CookieHelper cookieHelper = new CookieHelper();
			String cookieValue = cookieHelper.getValidCookieValue(req, "login");			
			logger.info("Cookie: " + cookieValue);

			if (reqURI.equalsIgnoreCase("/quality-manager-web/")
					|| reqURI.indexOf("index.jsf") >= 0
					|| reqURI.indexOf("index.xhtml") >= 0
					|| reqURI.indexOf("quemSomos.jsf") >= 0
					|| reqURI.indexOf("servidorHabilitado") >= 0
					|| reqURI.indexOf("usuarioRealServidorHabilitado") >= 0
					|| reqURI.indexOf("cadastrarServidorHabilitado") >= 0
					|| reqURI.indexOf("cadastroConcluido") >= 0							
					|| reqURI.indexOf("error-page.jsf") >= 0
					|| reqURI.indexOf("error-page.xhtml") >= 0
					|| (ses != null && ses.getAttribute("pessoaBean") != null)
					|| reqURI.contains("javax.faces.resource")
					|| reqURI.contains("resources")
					|| reqURI.contains("/templates/")) {

				logger.info("Redirect to: " + reqURI);
				
				/* Eliminar cache de recursos no browser.
				 * 
				 * if (!req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { // Skip JSF resources (CSS/JS/Images/etc)
					res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
					res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
					res.setDateHeader("Expires", 0); // Proxies.
		        }*/
				
				chain.doFilter(request, response);

			} else {
				
				/* 
				 * User didn't log in but asking for a page that is not allowed
				 * so take user to login page
				 * Anonymous user. Redirect to login page
				 */
				String redirect = req.getContextPath() + "/";
				logger.info("Redirect to login: " + redirect);

				res.sendRedirect(redirect);
			}
			
		} catch (Throwable t) {
			
			logger.error(t.getMessage());
		}
	}

	@Override
	public void destroy() {}
}