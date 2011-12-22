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
 * Checks out if user is logged in. Application allowed any action but the proper 
 * login only for people who know the password. 
 *  
 * @author gue
 */
public class LoginFilter implements Filter 
{
	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(final ServletRequest request, 
						 final ServletResponse response,
						 final FilterChain chain) throws IOException, ServletException 
	{
		
		final HttpServletRequest req = (HttpServletRequest) request; 
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
		
		final Object login = req.getSession().getAttribute("login"); 
		if (!req.getRequestURI().contains("login") && login == null || 
				!req.getRequestURI().contains("login") && (Boolean)login == false)
		{
			throw new IllegalStateException("No privileges for entry found!"); 
			// Session.createAlmenrauschSession(req, Permission.VISITOR); 
			
//			if (req.getRequestURI().contains("delete") || req.getRequestURI().contains("update"))
//			{
//				throw new IllegalStateException("Visitors are not allowed to delete or update!"); 	
//			}
		}
		
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
