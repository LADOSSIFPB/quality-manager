package filter;

import java.io.IOException;

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

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {
	
	private Logger log = LogManager.getLogger(AuthFilter.class);
	
	public AuthFilter() {}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			// check whether session variable is set
			HttpServletRequest req = (HttpServletRequest) request;
			
			HttpSession ses = req.getSession(false);
			
			// allow user to proccede if url is login.xhtml or user logged in or
			// user is accessing any page in //public folder
			String reqURI = req.getRequestURI();
			log.info("URI Requisition: " + reqURI);
			
			if (reqURI.equalsIgnoreCase("/quality-manager_WEB/")
					|| reqURI.equalsIgnoreCase("/QManager_WEB/")
					|| reqURI.indexOf("index.jsf") >= 0
					|| reqURI.indexOf("index.xhtml") >= 0
					|| reqURI.indexOf("buscarServidorHabilitado.xhtml") >= 0
					|| reqURI.indexOf("cadastrarServidorHabilitado.xhtml") >= 0
					|| reqURI.indexOf("error-page.jsf") >= 0
					|| reqURI.indexOf("error-page.xhtml") >= 0
					|| reqURI.indexOf("teste.jsf") >= 0 //TODO: remover para produção.
					|| (ses != null && ses.getAttribute("pessoaBean") != null)
					|| reqURI.contains("javax.faces.resource")
					|| reqURI.contains("/resources/")) {
				
				log.info("Redirect to: " + reqURI);
				chain.doFilter(request, response);
				
			} else {
				// user didn't log in but asking for a page that is not allowed
				// so take user to login page
				// Anonymous user. Redirect to login page
				
				String redirect = req.getContextPath() + "/";				
				log.info("Redirect to login: " + redirect);
				
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect(redirect);
			}
		} catch (Throwable t) {
			log.error(t.getMessage());
		}
	} // doFilter

	@Override
	public void destroy() {}
}