package de.iplabs.almenrausch;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

import de.iplabs.almenrausch.web.Router;

/**
 * Servlet to handle logout from system. 
 * 
 * @author gue
 */
@Singleton
public class LogoutServlet extends HttpServlet 
{
	/** The stupid UID. */
	private static final long serialVersionUID = -5223827392667346148L;
	
	/** The logger. **/
	private static final Logger log = Logger.getLogger(LogoutServlet.class.getName());
	
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		log.info("Logging out ..."); 
		req.getSession().removeAttribute("login");
		Router.redirect(resp, "/byebye.jsp"); 
	}
}
