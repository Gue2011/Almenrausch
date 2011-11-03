package de.iplabs.almenrausch.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Router 
{
	public static void goToViewTasks(final HttpServletRequest req, final HttpServletResponse resp) throws IOException
	{
		RequestDispatcher dispatcher = req.getRequestDispatcher("/viewTasks.jsp"); 
		try 
		{
			dispatcher.forward(req, resp);
		} 
		catch (ServletException e) 
		{
			throw new IllegalStateException("Could not forward request to showTasks.jsp", e); 
		} 
	}
	
	public static void redirect(final HttpServletResponse resp, final String target) throws IOException
	{
			resp.sendRedirect(target);
	}
}
