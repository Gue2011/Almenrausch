package de.iplabs.almenrausch;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.iplabs.almenrausch.model.MantisTask;
import de.iplabs.almenrausch.model.Session;
import de.iplabs.almenrausch.model.timeunit.Month;
import de.iplabs.almenrausch.model.timeunit.Week;
import de.iplabs.almenrausch.model.timeunit.Year;
import de.iplabs.almenrausch.persistent.TaskDao;
import de.iplabs.almenrausch.web.Router;

/**
 * The servlet that prepares the view for the viewTasks.jsp. 
 * Not really MVC-conform, but acceptable here. 
 * 
 * @author gue
 */
@SuppressWarnings("serial")
@Singleton
public class ViewTasksServlet extends HttpServlet 
{
	// The logger for this class. 
	private final static Logger log = Logger.getLogger(ViewTasksServlet.class.getName());

	/** The injected singleton instance of the TaskDao. **/
	private final TaskDao taskDao; 
	
	/**
	 * Constructor
	 * 
	 * @param taskDao
	 */
	@Inject
	public ViewTasksServlet (final TaskDao taskDao)
	{
		this.taskDao = taskDao; 
	}
	
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException 
	{
		final String weekString = req.getParameter("week"); 
		final String monthString = req.getParameter("month"); 
		final String yearString = req.getParameter("year"); 
		
		final Year year = yearString == null ? Year.getCurrentYear() : Year.valueOf("_"+yearString); 
		final Session session = Session.getCurrentSession(req); 
		session.setYear(year); 
		
		log.info("Requesting if for year+" +year+ ", kw "+weekString+", month "+monthString); 

		if (weekString != null)
		{
			final Week week = new Week (weekString == null ? 0 : Integer.parseInt(weekString)); 
			
			final List<MantisTask> tasks = this.taskDao.getTasksByCalendarWeek(week, year); 
		
			session.setCurrentTasks(tasks); 
			
			Router.goToViewTasks(req, resp); 
			return; 
		}
		
		final Month month = Month.getFromDate(monthString); 
		final List<MantisTask> tasks = this.taskDao.getTasksByMonth(month, year);
		
		session.setMonth(month); 
		session.setCurrentTasks(tasks); 
		
		Router.goToViewTasks(req, resp); 
	}
}
