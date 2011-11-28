package de.iplabs.almenrausch;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.iplabs.almenrausch.model.Session;
import de.iplabs.almenrausch.web.Router;

/**
 * Servlet that handles login stuff.
 * 
 * @author gue
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet 
{
	// A logger for this class. 
	Logger log = Logger.getLogger(LoginServlet.class.getName());
	
	private static int this_week; 
	
	static 
	{
		final GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTime(new Date()); 
		this_week = gc.get(GregorianCalendar.WEEK_OF_YEAR) - 1; 
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
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
		if ("wenzel009".equals(login))
		{
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
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		doPost(req, resp); 
	}
}
