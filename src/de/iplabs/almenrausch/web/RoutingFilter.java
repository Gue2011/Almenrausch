package de.iplabs.almenrausch.web;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter that logs out all calls to servlet infrastructure of this app to be able
 * to see the uses way through the application. 
 * 
 * @author gue
 */
public class RoutingFilter implements Filter 
{
	// Logger.
	private static final Logger log = Logger.getLogger(RoutingFilter.class.getName()); 
	
	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(final ServletRequest request, 
						 final ServletResponse response,
						 final FilterChain chain) throws IOException, ServletException 
	{
		final HttpServletRequest req = (HttpServletRequest) request; 
		log.info("Called resource: "+req.getRequestURI()); 
		chain.doFilter(request, response); 
	}

	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() { }

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException { }
}
