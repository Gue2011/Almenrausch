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

@SuppressWarnings("serial")
public class ViewTasksServlet extends HttpServlet {
	Logger log = Logger.getLogger(ViewTasksServlet.class.getName());

	public void doGet(final HttpServletRequest req, final HttpServletResponse resp)
			throws IOException {

		log.info("Going to ViewTasksServlet");
		
		final String weekString = req.getParameter("week"); 
		int week = weekString == null ? 0 : Integer.parseInt(weekString); 
		
		TaskDao dao = new TaskDao(); 
		List<MantisTask> tasks = dao.getTasksByCalendarWeek(week); 
		log.info(""); 
		Session session = (Session) req.getSession().getAttribute("session"); 
		if (session == null) 
		{
			session = new Session(); 
			req.getSession().setAttribute("session", session); 
		}
		session.setCurrentTasks(tasks); 
		
		Router.goToViewTasks(req, resp); 
	}
}
