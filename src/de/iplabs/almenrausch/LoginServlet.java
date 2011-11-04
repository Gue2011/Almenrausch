package de.iplabs.almenrausch;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.iplabs.almenrausch.web.Router;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	Logger log = Logger.getLogger(LoginServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		final String login = req.getParameter("pw"); 
		
		if (1 == 1)
		{
			req.getSession().setAttribute("login", true); 
			GregorianCalendar gc = new GregorianCalendar(); 
			gc.setTime(new Date()); 
			Router.redirect(resp, "/viewTasks?week="+gc.get(GregorianCalendar.WEEK_OF_YEAR - 1)); 
		}
		else 
		{
			Router.redirect(resp, "/loginFailed.jsp"); 
		}
			
	}
}
