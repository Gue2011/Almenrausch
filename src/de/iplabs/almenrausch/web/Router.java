package de.iplabs.almenrausch.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Encapsulates all routing with the request dispatcher, to keep servlets free 
 * from boilerplate that brings the servlet api. 
 * 
 * @author gue
 */
public class Router 
{
	/**
	 * Forward to the view Tasks jsp. 
	 * 
	 * @param req The request
	 * @param resp The response
	 * @throws IOException Is thrown if exceptional things happens. 
	 */
	public static void goToViewTasks(final HttpServletRequest req, final HttpServletResponse resp) throws IOException
	{
		resp.setContentType("text/html; charset=utf-8");
		forward(req, resp, "/viewTasks.jsp"); 
	}
	
	/**
	 * Do a redirect to the given target. 
	 * 
	 * @param resp The response. 
	 * @param target The target resource as a {@link String}
	 * @throws IOException Is thrown if exceptional things happens.
	 */
	public static void redirect(final HttpServletResponse resp, final String target) throws IOException
	{
		resp.sendRedirect(target);
	}
	
	/**
	 * Forward to the any view. 
	 * 
	 * @param req The request
	 * @param resp The response
	 * @param target The target
	 * 
	 * @throws IOException Is thrown if exceptional things happens. 
	 */
	public static void forward (final HttpServletRequest req, final HttpServletResponse resp, final String target) throws IOException
	{
		RequestDispatcher dispatcher = req.getRequestDispatcher(target); 
		try 
		{
			dispatcher.forward(req, resp);
		} 
		catch (ServletException e) 
		{
			throw new IllegalStateException("Could not forward request to "+target, e); 
		} 
	}
}