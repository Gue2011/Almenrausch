package de.iplabs.almenrausch;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.iplabs.almenrausch.model.MantisTask;
import de.iplabs.almenrausch.persistent.TaskDao;
import de.iplabs.almenrausch.web.Router;

@SuppressWarnings("serial")
public class LookupTaskServlet extends HttpServlet {
	Logger log = Logger.getLogger(LookupTaskServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		final String id = req.getParameter("id");  
	
		if (id == null) throw new IllegalArgumentException("No param id submitted!");  
		
		log.info("Looing up task: " + req.getParameter("id"));
		final Long taskId = Long.valueOf(id); 
		
		final TaskDao dao = new TaskDao(); 
		final MantisTask task = dao.getByTaskId(taskId); 
		
		System.out.println(task);
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
