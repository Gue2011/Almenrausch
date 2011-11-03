package de.iplabs.almenrausch;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.iplabs.almenrausch.web.Router;

@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {
	
	Logger log = Logger.getLogger(LogoutServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		req.getSession().removeAttribute("login");
		Router.redirect(resp, "/byebye.jsp"); 
		
			
	}
}
