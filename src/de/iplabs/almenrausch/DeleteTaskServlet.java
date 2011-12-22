package de.iplabs.almenrausch;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.iplabs.almenrausch.persistent.TaskDao;
import de.iplabs.almenrausch.web.Router;

/**
 * Servlet to delete tasks from db. 
 * 
 * @author gue
 */
@SuppressWarnings("serial")
@Singleton
public class DeleteTaskServlet extends HttpServlet 
{
	// A logger. 
	private static final Logger log = Logger.getLogger(DeleteTaskServlet.class.getName());

	/** The injected singleton instance of the TaskDao. **/
	private final TaskDao taskDao; 
	
	/**
	 * Constructor
	 * 
	 * @param taskDao
	 */
	@Inject
	public DeleteTaskServlet (final TaskDao taskDao)
	{
		this.taskDao = taskDao; 
	}
	
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
		
		final boolean result = this.taskDao.deleteTask(taskId); 
		
		if (result) log.info("Task "+id+" has been deleted!"); 
		else log.info("Could not find task "+id+" in DB!"); 
		
		Router.redirect(resp, "/viewTasks?week="+week); 
	}
}
