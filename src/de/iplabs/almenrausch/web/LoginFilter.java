package de.iplabs.almenrausch.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter 
{
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException 
	{
		
		HttpServletRequest req = (HttpServletRequest) request; 
		if ("/".equals(req.getRequestURI())) 
		{
			chain.doFilter(request, response); 
			return; 
		}
		
		if (req.getRequestURI().contains("byebye")) 
		{
			chain.doFilter(request, response); 
			return; 
		}
		
		Object login = req.getSession().getAttribute("login"); 
		if (!req.getRequestURI().contains("login") && login == null)
			throw new IllegalStateException("Unallowed visitor! Please log in to authentify!"); 
		
		if (!req.getRequestURI().contains("login") && (Boolean)login == false)
			throw new IllegalStateException("Unallowed visitor! Please log in to authentify!"); 
		
		chain.doFilter(request, response); 
		
	}

	@Override
	public void destroy() { }

	@Override
	public void init(FilterConfig arg0) throws ServletException { }
}
