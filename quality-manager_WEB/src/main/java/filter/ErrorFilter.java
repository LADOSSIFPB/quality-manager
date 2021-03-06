package filter;

import java.io.IOException;

import javax.el.PropertyNotFoundException;
import javax.faces.FacesException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managedBean.PathRedirect;

import org.apache.http.HttpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(filterName = "ErrorFilter", urlPatterns = { "/*" })
public class ErrorFilter implements Filter {

	private Logger logger = LogManager.getLogger(AuthFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
    	
    	// Throw wrapped ViewExpiredException instead of ServletException.
    	StringBuffer redirect = new StringBuffer(req.getContextPath());
    	
		try {
	        
			chain.doFilter(request, response);
			
	    } catch (ServletException servletException) {
	        
	    	logger.error("Redirecionamento de erros e exceções.");
	    	logger.error(servletException);    	
	    	
	    	Throwable rootCause = servletException.getRootCause();
	        
	    	if (rootCause instanceof FacesException 
	    			|| rootCause instanceof RuntimeException) {
	    		
	    		redirect.append(PathRedirect.webAppBase 
	    				+ PathRedirect.errorPage);
	    		
	    	} else if (rootCause instanceof HttpException) {
	    		
	    		//TODO: Verificar códigos http.
	    		redirect.append(PathRedirect.webAppBase 
	    				+ PathRedirect.errorPage);
	        } else {	        	
	        	
	        	// Exception desconhecida.
	        	redirect.append(PathRedirect.webAppBase 
	    				+ PathRedirect.errorPage);
	        }
	        
	        HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(redirect.toString());
	    
		} catch (FacesException | PropertyNotFoundException e) {
			
			logger.error("Redirecionamento de erros e exceções.");
			logger.error(e);

			redirect.append(PathRedirect.webAppBase + PathRedirect.errorPage);
			
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(redirect.toString());
		}
	}
	
	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}