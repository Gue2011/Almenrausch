package de.iplabs.almenrausch;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Servlet to write an updated task to database. 
 * 
 * @author gue
 */
@Singleton
public class UpdateTaskSubmitServlet extends HttpServlet 
{
	/** The UID. */
	private static final long serialVersionUID = -1032231102610716464L;

	/** The logger. **/
	private final static Logger log = Logger.getLogger(UpdateTaskSubmitServlet.class.getName());

	/** The injected singleton instance of the TaskDao. **/
	private final TaskDao taskDao; 
	
	/**
	 * Constructor
	 * 
	 * @param taskDao
	 */
	@Inject
	public UpdateTaskSubmitServlet (final TaskDao taskDao)
	{
		this.taskDao = taskDao; 
	}
	
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		final String id = req.getParameter("id"); 
		if (id == null) throw new IllegalArgumentException("No param id submitted!"); 
		
		final String eff = req.getParameter("effort").replace(',', '.');
		
		log.info("Id: " + req.getParameter("id"));
		final Long taskId = Long.valueOf(id); 
		
		final MantisTask task = this.taskDao.getByTaskId(taskId); 
		
		log.info("Date: " + req.getParameter("date"));
		log.info("Effort: " + eff);
		log.info("Descritpion: " + req.getParameter("description"));
		log.info("Payer: " + req.getParameter("payer"));

		final String description = req.getParameter("description");
		final BigDecimal effort = new BigDecimal(eff);
		final String payer = req.getParameter("payer"); 

		Date date; 
		try 
		{
			final SimpleDateFormat sdfToDate = new SimpleDateFormat("yyyy-MM-dd");
			date = sdfToDate.parse(req.getParameter("date"));
		} 
		catch (ParseException e) 
		{
			throw new IllegalArgumentException("Cannot parse date "+req.getParameter("date"), e); 
		}
		
		task.setDate(date); 
		task.setDescription(description); 
		task.setEffort(effort.doubleValue()); 
		task.setPayer(payer); 
	
		this.taskDao.update(task); 
		
		Router.redirect(resp, "viewTasks?week="+task.getCalenderWeek()); 
	}
}
