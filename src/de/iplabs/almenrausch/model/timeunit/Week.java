package de.iplabs.almenrausch.model.timeunit;

import com.google.appengine.repackaged.com.google.common.base.Predicate;

import de.iplabs.almenrausch.model.MantisTask;

/**
 * Represents one week and can do everything that is needed around weeks. 
 * 
 * @author gue
 */
public class Week 
{
	/** The calendar week that this week represents. **/
	private int calendarweek; 
	
	public Week (final int calendarweek)
	{
		if (calendarweek < 0) throw new IllegalArgumentException("Argument calendarweek cannnot be less than 1"); 
		if (calendarweek > 52) throw new IllegalArgumentException("Argument calendarweek cannnot be greater than 52"); 
		
		this.calendarweek = calendarweek; 
	}
	
	public Week (final String calendarweek)
	{
		if (calendarweek == null) throw new IllegalArgumentException("Argument calendarweek must not be null!"); 
		this.calendarweek = Integer.valueOf(calendarweek); 
	}
	
	public Predicate<MantisTask> isTaskInThisWeek(final Year year) 
	{
		return new Predicate<MantisTask>() 
		{
			@Override
			public boolean apply(final MantisTask task) 
			{
				return task.getCalenderWeek() == calendarweek && task.getYear() == year.asInt(); 
			}
		};
	}
}