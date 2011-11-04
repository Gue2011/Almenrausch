package de.iplabs.almenrausch.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ChromeFilter implements Filter 
{
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest) request; 
		final String userAgent = (req.getHeader("User-Agent")); 
		if (!userAgent.contains("Chrome")) throw new IllegalStateException("Application can only be used by Chrome Users!"); 
		chain.doFilter(request, response); 
	}

	@Override
	public void destroy() { }

	@Override
	public void init(FilterConfig arg0) throws ServletException { }
}
