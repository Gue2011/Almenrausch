package de.iplabs.almenrausch;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.iplabs.almenrausch.model.MantisTask;
import de.iplabs.almenrausch.persistent.TaskDao;
import de.iplabs.almenrausch.web.Router;

/**
 * Servlet to look up single tasks from db.
 * 
 * @author gue
 */
@Singleton
public class LookupTaskServlet extends HttpServlet 
{
	/** The UID. */
	private static final long serialVersionUID = 6813712400046153955L;

	/** The logger. **/
	private static final Logger log = Logger.getLogger(LookupTaskServlet.class.getName());

	/** The injected singleton instance of the TaskDao. **/
	private final TaskDao taskDao; 
	
	/**
	 * Constructor
	 * 
	 * @param taskDao
	 */
	@Inject
	public LookupTaskServlet (final TaskDao taskDao)
	{
		this.taskDao = taskDao; 
	}
	
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		final String id = req.getParameter("id");  
	
		if (id == null) throw new IllegalArgumentException("No param id submitted!");  
		
		log.info("Looing up task: " + req.getParameter("id"));

		final Long taskId = Long.valueOf(id); 
		final MantisTask task = this.taskDao.getByTaskId(taskId); 
		
		if (task == null)
		{
			Router.redirect(resp, "/lookupTask.jsp?idx="+id); 
		}
		else
		{
			req.setAttribute("id", task.getId());
			req.setAttribute("description", task.getDescription());
			req.setAttribute("effort", task.getEffort());
			req.setAttribute("payer", task.getPayer());
			final SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
			req.setAttribute("date", df.format(task.getDate()));
			req.setAttribute("week", task.getCalenderWeek());
			
			Router.forward(req, resp, "/lookupTask.jsp"); 
		}
	}
}
