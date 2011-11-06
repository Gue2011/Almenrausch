package de.iplabs.almenrausch.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * App can only be used by Chrome users due to incomplete HTML5 implementations of other browsers. 
 * 
 * @author gue
 */
public class ChromeFilter implements Filter 
{
	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(final ServletRequest request, 
						 final ServletResponse response,
						 final FilterChain chain) throws IOException, ServletException 
	{
//		final HttpServletRequest req = (HttpServletRequest) request; 
//		final String userAgent = (req.getHeader("User-Agent")); 
//		if (!userAgent.contains("Chrome")) throw new IllegalStateException("Application can only be used by Chrome Users!"); 
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
