package de.iplabs.almenrausch;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

import de.iplabs.almenrausch.web.Router;

/**
 * Servlet that redirects directly to the vie Tasks page, entering as a visitor then. 
 * TODO: Re-think this idea!
 * 
 * @author gue
 */
@Singleton
public class EnterAsVisitorServlet extends HttpServlet 
{
	/** The logger. */
	private static final long serialVersionUID = -427207181666331934L;

	/** A logger for this class. **/
	private final static Logger log = Logger.getLogger(EnterAsVisitorServlet.class.getName());
	
	private static int this_week; 
	
	static 
	{
		final GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTime(new Date()); 
		this_week = gc.get(GregorianCalendar.WEEK_OF_YEAR) - 1; 
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		Router.redirect(resp, "/viewTasks?week="+this_week); 
		return; 
	}
	
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		doPost(req, resp); 
	}
}
