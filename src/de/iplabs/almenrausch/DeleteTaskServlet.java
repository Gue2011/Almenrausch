package de.iplabs.almenrausch;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.iplabs.almenrausch.persistent.TaskDao;
import de.iplabs.almenrausch.web.Router;

/**
 * Servlet to delete tasks from db. 
 * 
 * @author gue
 */
@SuppressWarnings("serial")
public class DeleteTaskServlet extends HttpServlet 
{
	// A logger. 
	private static final Logger log = Logger.getLogger(DeleteTaskServlet.class.getName());

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		final String id = req.getParameter("id"); 
		final String week = req.getParameter("week"); 
	
		if (id == null) throw new IllegalArgumentException("No param id submitted!");  
		if (week == null) throw new IllegalArgumentException("No param week submitted!");  
		
		log.info("Id: " + req.getParameter("id"));
		final Long taskId = Long.valueOf(id); ; 
		
		final TaskDao dao = new TaskDao(); 
		final boolean result = dao.deleteTask(taskId); 
		
		if (result) log.info("Task "+id+" has been deleted!"); 
		else log.info("Could not find task "+id+" in DB!"); 
		
		Router.redirect(resp, "/viewTasks?week="+week); 
	}
}
