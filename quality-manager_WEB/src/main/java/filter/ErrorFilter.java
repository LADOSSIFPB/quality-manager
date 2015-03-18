package filter;

import java.io.IOException;

import javax.faces.application.ViewExpiredException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(filterName = "ErrorFilter", urlPatterns = { "/*" })
public class ErrorFilter implements Filter {

	private Logger logger = LogManager.getLogger(AuthFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		try {
	        
			chain.doFilter(request, response);
			
	    } catch (ServletException e) {
	        
	    	logger.error("Lançamento de erro: " + e.getMessage());
	    	
	    	Throwable rootCause = e.getRootCause();
	        
	        if (rootCause instanceof ViewExpiredException) { // This is true for any FacesException.
	            
	        	throw (ViewExpiredException) rootCause; // Throw wrapped ViewExpiredException instead of ServletException.
	        
	        } else if (rootCause instanceof RuntimeException) { // This is true for any FacesException.
	            
	        	throw (RuntimeException) rootCause; // Throw wrapped RuntimeException instead of ServletException.
	        
	        } else {
	            
	        	throw e;
	        }
	    }
		
	}
	
	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}
