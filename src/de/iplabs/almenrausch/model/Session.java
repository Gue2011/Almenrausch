package de.iplabs.almenrausch.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.repackaged.com.google.common.base.Predicate;
import com.google.appengine.repackaged.com.google.common.collect.Collections2;
import com.google.appengine.repackaged.com.google.common.collect.Lists;

import de.iplabs.almenrausch.model.timeunit.Month;
import de.iplabs.almenrausch.model.timeunit.Year;

/**
 * Container with some service functionality for the front end that encapsulates
 * all stuff that is constantly stored for one users' session. 
 * 
 * @author gue
 */
public class Session implements Serializable
{
	/**
	 * The UUID.  
	 */
	private static final long serialVersionUID = 4653592975794575821L;
	
	/** All current mantis tasks. **/
	private List<MantisTask> currentTasks;
	
	/** The users permission level. **/
	private Permission permission = Permission.ADMIN; 
	
	/** The month (cached here). **/
	private Month month; 
	
	/** The year (cached here). **/
	private Year year; 
	
	/**
	 * Hidden constructor. 
	 */
	private Session(){ }

	/**
	 * @return all tasks ordered by M�nig.
	 */
	public List<MantisTask> getCurrentMoenigTasks() 
	{
		return getTasksByStringPredicate("Fuji"); 
	}
	
	/**
	 * @return all tasks ordered by Direkt payers.
	 */
	public List<MantisTask> getCurrentDirectTasks() 
	{
		return getTasksByStringPredicate("Direkt"); 
	}
	
	/**
	 * @param predicate May be 'Fuji' or 'Direkt'
	 * @return The Tasks that belong to the respective target group. 
	 */
	private List<MantisTask> getTasksByStringPredicate (final String predicate)
	{
		if (predicate == null) throw new IllegalArgumentException("Argument predicate must not be null!");
		
		final Collection<MantisTask> result = Collections2.filter(this.currentTasks, new Predicate<MantisTask>() 
		{
			@Override
			public boolean apply(MantisTask mt) 
			{
				return (predicate.equals(mt.getPayer()));
			}
		});
		return Lists.newArrayList(result); 
	}

	/**
	 * @param currentTasks the currentTasks to set
	 */
	public void setCurrentTasks(final List<MantisTask> currentTasks) 
	{
		if (this.currentTasks == null) this.currentTasks = new ArrayList<MantisTask>(); 
		this.currentTasks.clear(); 
		for (final MantisTask t : currentTasks)
		{
			this.currentTasks.add(t); 
		}
	} 
	
	/**
	 * @return The number of all tasks. 
	 */
	public int getNumberOfTasks()
	{
		return this.currentTasks.size(); 
	}
	
	/**
	 * @return The sum of all M�nig-Tasks as a formatted string. 
	 */
	public String getSumOfMoenigTasks()
	{
		double sum = 0.0; 
		for (MantisTask t : this.currentTasks) 
		{
			if ("Fuji".equals(t.getPayer())) sum += t.getEffort(); 
		}
		
		return toOutputFormat(sum); 
	}
	
	
	public List<String> getWeeksBefore()
	{
		final GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTime(new Date()); 
		
		final List<String> weeksBefore = new ArrayList<String>(); 
		for (int i = 1; i < 5; i ++) weeksBefore.add((gc.get(GregorianCalendar.WEEK_OF_YEAR) - i+"")); 
		return weeksBefore; 
	}
	
	
	public List<String> getMonthBefore()
	{
		final GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTime(new Date()); 
		
		final List<String> monthBefore = new ArrayList<String>(); 
		for (int i = 0; i < 4; i ++) monthBefore.add((gc.get(GregorianCalendar.MONTH) + 1 - i+"")); 
		return monthBefore; 
	}
	
	/**
	 * @return True if user is an admin, false if not.
	 */
	public boolean isAdmin()
	{
		return (this.permission == Permission.ADMIN); 
	}

	/**
	 * @return The sum of all Direkt-Tasks as a formatted string. 
	 */
	public String getSumOfDirectTasks()
	{
		double sum = 0.0; 
		for (MantisTask t : this.currentTasks) 
		{
			if ("Direkt".equals(t.getPayer())) sum += t.getEffort(); 
		}
		
		return toOutputFormat(sum); 
	}
	
	public void setMonth (final Month month)
	{
		if (month == null) throw new IllegalArgumentException("Argument month must not be null!"); 
		this.month = month; 
	}
	
	public String getCurrentMonth()
	{
		if (this.month == null) throw new IllegalStateException("Currently no month in cache!"); 
		return this.month.getName(); 
	}
	
	/**
	 * @return the year
	 */
	public String getCurrentYear() 
	{
		if (this.year == null) throw new IllegalStateException("Currently no year in cache!"); 
		return year.asInt()+"";
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Year year) 
	{
		if (year == null) throw new IllegalArgumentException("Argument month must not be null!"); 
		this.year = year;
	}

	/**
	 * @param effort A value that is meant as person hours as a double-value. 
	 * @return A formatted string containing days and hours, thus something like '2 Tag(e) 4.5 Stunde(n)'
	 */
	private static String toOutputFormat (final double effort)
	{
		double sumDD = Math.floor(effort/8.0);
		double sumHH = effort - sumDD * 8; 
		
		return sumDD+" Tag(e) "+new BigDecimal(sumHH).setScale(1, BigDecimal.ROUND_HALF_UP)+ " Stunde(n)"; 
	}

	/**
	 * @param request The current {@link HttpServletRequest}
	 * @return The current session if there is already one, creates a new instance if not. 
	 */
	public static Session getCurrentSession (final HttpServletRequest request)
	{
		if (request == null) throw new IllegalArgumentException("Argument request must not be null!"); 
		
		Session session = (Session) request.getSession().getAttribute("session"); 
		if (session == null) 
		{
			session = new Session(); 
			request.getSession().setAttribute("session", session); 
		}
		return session;
	}
	
	/**
	 * Create a session with visitor rights level (restricted to read only)
	 * @param request The current session if there is already one, creates a new instance if not. 
	 */
	public static void createAlmenrauschSession (final HttpServletRequest request, final Permission permission)
	{
		if (request == null) throw new IllegalArgumentException("Argument request must not be null!"); 

		final Session session = new Session(); 
		session.permission = permission; 
		request.getSession().setAttribute("session", session); 
	}
}