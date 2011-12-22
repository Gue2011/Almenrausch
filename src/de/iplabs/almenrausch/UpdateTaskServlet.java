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
 * Servlet to enter update task view. 
 * 
 * @author gue
 */
@Singleton
public class UpdateTaskServlet extends HttpServlet 
{
	/** The UID. */
	private static final long serialVersionUID = -5748473191590385378L;

	/** The logger. **/
	private final static Logger log = Logger.getLogger(UpdateTaskServlet.class.getName());

	/** The injected singleton instance of the TaskDao. **/
	private final TaskDao taskDao; 
	
	/**
	 * Constructor
	 * 
	 * @param taskDao
	 */
	@Inject
	public UpdateTaskServlet (final TaskDao taskDao)
	{
		this.taskDao = taskDao; 
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		final String id = req.getParameter("id"); 
		final String week = req.getParameter("week"); 
	
		if (id == null) throw new IllegalArgumentException("No param id submitted!");  
		if (week == null) throw new IllegalArgumentException("No param week submitted!");  
		
		log.info("Id: " + req.getParameter("id"));
		final Long taskId = Long.valueOf(id); 
		
		MantisTask task = this.taskDao.getByTaskId(taskId); 
		
		req.setAttribute("id", task.getId());
		req.setAttribute("description", task.getDescription());
		req.setAttribute("effort", task.getEffort());
		req.setAttribute("payer", task.getPayer());
		final SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		req.setAttribute("date", df.format(task.getDate()));
		req.setAttribute("week", week);
		
		Router.forward(req, resp, "/updateTask.jsp");  
	}
}
