package de.iplabs.almenrausch;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		final String login = req.getParameter("pw"); 
		
		log.info("Enter some password detenction in produc tion environments!"); 
		// Add something like "somePassword".equals(login)
		if (1 == 1)
		{
			req.getSession().setAttribute("login", true); 
			final GregorianCalendar gc = new GregorianCalendar(); 
			gc.setTime(new Date()); 
			Router.redirect(resp, "/viewTasks?week="+gc.get(GregorianCalendar.WEEK_OF_YEAR - 1)); 
		}
		else 
		{
			Router.redirect(resp, "/loginFailed.jsp"); 
		}
			
	}
}
