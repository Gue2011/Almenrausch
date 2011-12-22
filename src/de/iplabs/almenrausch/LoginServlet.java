package de.iplabs.almenrausch;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

import de.iplabs.almenrausch.model.Permission;
import de.iplabs.almenrausch.model.Session;
import de.iplabs.almenrausch.web.Router;

/**
 * Servlet that handles login stuff.
 * 
 * @author gue
 */
@Singleton
public class LoginServlet extends HttpServlet 
{
	/** The serial UID. */
	private static final long serialVersionUID = 1L;

	/** A logger for this class. **/
	Logger log = Logger.getLogger(LoginServlet.class.getName());
	
	/** The current week. **/
	private static int this_week; 
	
	static 
	{
		updateCurrentWeek(); 
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		updateCurrentWeek(); 
		final String login = req.getParameter("pw"); 

		if (req.getSession().getAttribute("login") != null)
		{
			boolean loggedIn = (Boolean) req.getSession().getAttribute("login"); 
			if (loggedIn) 
			{
				Router.redirect(resp, "/viewTasks?week="+this_week); 
				return; 
			}
			else
			{
				throw new IllegalStateException("Logins cannot be false. Check application!"); 
			}
		}
		
		log.info("Enter some password detection in production environments!"); 
		// Add something like "somePassword".equals(login)
		if ("xxx".equals(login))
		{
			Session.createAlmenrauschSession(req, Permission.ADMIN); 
			req.getSession().setAttribute("login", true); 
			final GregorianCalendar gc = new GregorianCalendar(); 
			gc.setTime(new Date()); 
			
			Router.redirect(resp, "/viewTasks?week="+this_week); 
		}
		else 
		{
			Router.redirect(resp, "/loginFailed.jsp"); 
		}
			
	}
	
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		doPost(req, resp); 
	}
	
	/**
	 * Set the current week into the static member of this class. 
	 */
	private static void updateCurrentWeek()
	{
		final GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTime(new Date()); 
		this_week = gc.get(GregorianCalendar.WEEK_OF_YEAR) - 1; 
	}
}
