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

import de.iplabs.almenrausch.model.MantisTask;
import de.iplabs.almenrausch.persistent.TaskDao;
import de.iplabs.almenrausch.web.Router;

@SuppressWarnings("serial")
public class UpdateTaskSubmitServlet extends HttpServlet {
	Logger log = Logger.getLogger(UpdateTaskSubmitServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		final String id = req.getParameter("id"); 
		if (id == null) throw new IllegalArgumentException("No param id submitted!"); 
		
		String eff = req.getParameter("effort").replace(',', '.');
		
		log.info("Id: " + req.getParameter("id"));
		final Long taskId = Long.valueOf(id); 
		
		TaskDao dao = new TaskDao(); 
		MantisTask task = dao.getByTaskId(taskId); 
		
		log.info("Date: " + req.getParameter("date"));
		log.info("Effort: " + eff);
		log.info("Descritpion: " + req.getParameter("description"));
		log.info("Payer: " + req.getParameter("payer"));

		String description = req.getParameter("description");
		BigDecimal effort = new BigDecimal(eff);
		String payer = req.getParameter("payer"); 

		Date date; 
		try 
		{
			SimpleDateFormat sdfToDate = new SimpleDateFormat(
					"yyyy-MM-dd");
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
		
		dao = new TaskDao(); 
		dao.update(task); 
		
		Router.redirect(resp, "viewTasks?week="+task.getCalenderWeek()); 
	}
}
