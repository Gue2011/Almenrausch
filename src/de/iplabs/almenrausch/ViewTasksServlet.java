package de.iplabs.almenrausch;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.iplabs.almenrausch.model.Session;
import de.iplabs.almenrausch.model.MantisTask;
import de.iplabs.almenrausch.persistent.TaskDao;
import de.iplabs.almenrausch.web.Router;

/**
 * The servlet that prepares the view for the viewTasks.jsp. 
 * Not really MVC-conform, but acceptable here. 
 * 
 * @author gue
 */
@SuppressWarnings("serial")
public class ViewTasksServlet extends HttpServlet 
{
	// The logger for this class. 
	Logger log = Logger.getLogger(ViewTasksServlet.class.getName());

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException 
	{
		final String weekString = req.getParameter("week"); 
		int week = weekString == null ? 0 : Integer.parseInt(weekString); 
		
		final TaskDao dao = new TaskDao(); 
		final List<MantisTask> tasks = dao.getTasksByCalendarWeek(week); 
		
		final Session session = Session.getCurrentSession(req); 
		session.setCurrentTasks(tasks); 
		
		Router.goToViewTasks(req, resp); 
	}
}
