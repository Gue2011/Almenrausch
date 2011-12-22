package de.iplabs.almenrausch.model.timeunit;

import java.util.Collection;

import com.google.appengine.repackaged.com.google.common.base.Predicate;

import de.iplabs.almenrausch.model.MantisTask;

public enum Month 
{
	JANUARY ("Januar", 1), 
	
	FEBRUARY ("Februar", 2),
	
	MARCH ("MŠrz", 3), 
	
	APRIL ("April", 4), 
	
	MAY ("Mai", 5), 
	
	JUNE ("Juni", 6), 
	
	JULI ("Juli", 7), 
	
	AUGUST ("August", 8), 
	
	SEPTEMBER ("September", 9), 
	
	OCTOBER ("Oktober", 10), 
	
	NOVEMBER ("November", 11), 
	
	DECEMBER ("Dezember", 12); 
	
	/** The name of the month: **/
	private final String name; 
	
	/** The months number as an int. **/
	private final int date; 
	
	/** The cached month predicate. **/	
	private Predicate<MantisTask> monthPreditcate; 
	
	/**
	 * Constructor. 
	 * 
	 * @param name
	 * @param date
	 */
	private Month (final String name, final int date)
	{
		this.name = name;
		this.date = date; 
	}

	/**
	 * @return the name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * @return the date
	 */
	public int getDate() 
	{
		return date;
	}
	
	/**
	 * @param date The date as a month string, e.g. '4' for April. 
	 * @return The {@link Month} in a type safe representation. 
	 */
	public static Month getFromDate (final String date)
	{
		if (date == null) throw new IllegalArgumentException("Argument date must not be null!"); 
		int dateInt = Integer.valueOf(date); 
		
		for (final Month m : values())
		{
			if (m.getDate() == dateInt)
			{
				return m; 
			}
		}
		throw new IllegalStateException("A month with that date number "+date+" does not exist!"); 
	}
	
	/**
	 * A Predicate for {@link MantisTask}s in {@link Collection}s that finds these in this month and the given year. 
	 * 
	 * @param year The year in which the task was scheduled. 
	 * @return A search {@link Predicate} for Tasks for month and yaer. 
	 */
	public Predicate<MantisTask> isTaskInThisMonth(final Year year) 
	{
		if (this.monthPreditcate == null)
		{
			this.monthPreditcate = new Predicate<MantisTask>() 
			{
				@Override
				public boolean apply(final MantisTask task) 
				{
					return task.getMonth() == date && task.getYear() == year.asInt(); 
				}
			};
		}
		return this.monthPreditcate; 
	}
}