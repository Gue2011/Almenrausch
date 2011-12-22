package de.iplabs.almenrausch;

import java.io.IOException;
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
 * Servlet to add a task to the database. 
 * 
 * @author Gue
 */
@SuppressWarnings("serial")
@Singleton
public class AddTaskServlet extends HttpServlet 
{
	// Logger
	Logger log = Logger.getLogger(AddTaskServlet.class.getName());

	/** The injected singleton instance of the TaskDao. **/
	private final TaskDao taskDao; 
	
	/**
	 * Constructor
	 * 
	 * @param taskDao
	 */
	@Inject
	public AddTaskServlet (final TaskDao taskDao)
	{
		this.taskDao = taskDao; 
	}
	
	/*
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		req.setCharacterEncoding("US-ASCII"); 
		final String eff = req.getParameter("effort").replace(',', '.');
		
		log.info("Date: " + req.getParameter("date"));
		log.info("Effort: " + eff);
		log.info("Descritpion: " + req.getParameter("description"));
		log.info("Id: " + req.getParameter("id"));
		log.info("Payer: " + req.getParameter("payer"));

		final Long id = Long.parseLong(req.getParameter("id"));
		final String description = req.getParameter("description");
		final Double effort = Double.valueOf(eff);
		final String payer = req.getParameter("payer"); 

		Date date; 
		try 
		{
			final SimpleDateFormat sdfToDate = new SimpleDateFormat(
					"yyyy-MM-dd");
			date = sdfToDate.parse(req.getParameter("date"));
		} 
		catch (ParseException e) 
		{
			throw new IllegalArgumentException("Cannot parse date "+req.getParameter("date"), e); 
		}

		final MantisTask task = new MantisTask(id, date, description, effort, payer); 
		log.info("Saving task"); 
		this.taskDao.save(task); 
		
		final int kw = task.getCalenderWeek(); 
		final int year = task.getYear(); 

		Router.redirect(resp, "/viewTasks?week="+kw+"&year="+year); 
	}
}