package de.iplabs.almenrausch;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import de.iplabs.almenrausch.persistent.TaskDao;

/**
 * The application config of the Almenrausch. 
 * managed by Googles Guice. 
 * 
 * @author gue
 */
public class GuiceServletConfig extends GuiceServletContextListener
{
	/** A logger. **/
	private final static Logger log = Logger.getLogger(GuiceServletConfig.class.getName()); 
	
	/**
	 * The servlet mapping of this application.
	 */
	private static Map<String, Class<? extends HttpServlet>> servletMappings = new HashMap<String, Class<? extends HttpServlet>>(); 
	
	static 
	{
		servletMappings.put("/viewTasks", 			ViewTasksServlet.class); 
		servletMappings.put("/addTask", 			AddTaskServlet.class); 
		servletMappings.put("/deleteTask", 			DeleteTaskServlet.class); 
		servletMappings.put("/login",	 			LoginServlet.class); 
		servletMappings.put("/logout", 				LogoutServlet.class); 
		servletMappings.put("/lookupTask",			LookupTaskServlet.class); 
		servletMappings.put("/updateTask", 			UpdateTaskServlet.class); 
		servletMappings.put("/updateTaskSubmit", 	UpdateTaskSubmitServlet.class); 
		servletMappings.put("/visit", 				EnterAsVisitorServlet.class); 
	}
	
	@Override
	protected Injector getInjector() 
	{
		return Guice.createInjector(
		new ServletModule()
		{
			@Override
			protected void configureServlets() 
			{
				for (final String mapping : servletMappings.keySet())
				{
					log.info("Injecting servlets: Routing '"+mapping+"' to class "+servletMappings.get(mapping).getSimpleName()); 
					serve(mapping).with(servletMappings.get(mapping)); 
				}
			}
		}, 
		new AbstractModule() 
		{
			@Override
			protected void configure() 
			{
				bind(TaskDao.class); 
			}
		});
	}
}