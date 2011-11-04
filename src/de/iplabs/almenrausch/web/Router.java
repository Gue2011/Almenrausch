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
		final RequestDispatcher dispatcher = req.getRequestDispatcher("/viewTasks.jsp"); 
		try 
		{
			dispatcher.forward(req, resp);
		} 
		catch (ServletException e) 
		{
			throw new IllegalStateException("Could not forward request to showTasks.jsp", e); 
		} 
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
}
