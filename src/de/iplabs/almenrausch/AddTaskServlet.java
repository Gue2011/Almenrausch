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
public class AddTaskServlet extends HttpServlet {
	Logger log = Logger.getLogger(AddTaskServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
		{

		String eff = req.getParameter("effort").replace(',', '.');
		
		log.info("Date: " + req.getParameter("date"));
		log.info("Effort: " + eff);
		log.info("Descritpion: " + req.getParameter("description"));
		log.info("Id: " + req.getParameter("id"));
		log.info("Payer: " + req.getParameter("payer"));

		Long id = Long.parseLong(req.getParameter("id"));
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

		MantisTask task = new MantisTask(id, date, description, effort.doubleValue(), payer); 
		TaskDao dao = new TaskDao(); 
		log.info("Saving task"); 
		dao.save(task); 
		
		final int kw = task.getCalenderWeek(); 

		Router.redirect(resp, "/viewTasks?week="+kw); 
	}
}
