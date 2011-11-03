package de.iplabs.almenrausch;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.iplabs.almenrausch.model.MantisTask;
import de.iplabs.almenrausch.persistent.TaskDao;

@SuppressWarnings("serial")
public class UpdateTaskServlet extends HttpServlet {
	Logger log = Logger.getLogger(UpdateTaskServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		final String id = req.getParameter("id"); 
		final String week = req.getParameter("week"); 
	
		if (id == null) throw new IllegalArgumentException("No param id submitted!");  
		if (week == null) throw new IllegalArgumentException("No param week submitted!");  
		
		log.info("Id: " + req.getParameter("id"));
		final Long taskId = Long.valueOf(id); 
		
		final TaskDao dao = new TaskDao(); 
		MantisTask task = dao.getByTaskId(taskId); 
		
		req.setAttribute("id", task.getId());
		req.setAttribute("description", task.getDescription());
		req.setAttribute("effort", task.getEffort());
		req.setAttribute("payer", task.getPayer());
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		req.setAttribute("date", df.format(task.getDate()));
		req.setAttribute("week", week);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/updateTask.jsp"); 
		try 
		{
			dispatcher.forward(req, resp);
		} 
		catch (ServletException e) 
		{
			throw new IllegalStateException("Could not forward request to showTasks.jsp", e); 
		} 
	}
}
