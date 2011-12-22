package de.iplabs.almenrausch.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * An object that represents on task in the mantis system. 
 * 
 * @author gue
 */
@PersistenceCapable(detachable="true")
public class MantisTask 
{
	/** The id of the task. **/
	@PrimaryKey  
	private Long taskId; 
	/** The date when it was scheduled for execution. **/
	@Persistent
	private Date date; 
	/** The description of the task from the Mantis system. **/
	@Persistent
	private String description; 
	/** The estimated effort that is supposed to be spent on this item. **/
	@Persistent
	private double effort;
	/** The customer group the pays for this task.  **/
	@Persistent
	private String payer; 

	
	/**
	 * @param id The id of the task.
	 * @param date The date when it was scheduled for execution.
	 * @param descritpion The description of the task from the Mantis system.
	 * @param effort The estimated effort that is supposed to be spent on this item.
	 */
	public MantisTask(final Long taskId, final Date date, final String descritpion,final  double effort, final String payer) 
	{
		if (date == null) throw new IllegalArgumentException("Argument date must not e null!"); 
		if (taskId == null) throw new IllegalArgumentException("Argument id must not e null!"); 
		if (descritpion == null) throw new IllegalArgumentException("Argument descritpion must not e null!"); 
		if (payer == null) throw new IllegalArgumentException("Argument payer must not e null!"); 
		
		this.taskId = taskId;
		this.date = date;
		this.description = descritpion;
		this.effort = effort;
		this.payer = payer; 
	}
	
	/**
	 * @return The calendar week in which this task was scheduled. 
	 */
	public int getCalenderWeek()
	{
		final GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTime(this.date); 
		return gc.get(GregorianCalendar.WEEK_OF_YEAR) - 1; 
	}

	/**
	 * @return The month in which this task was scheduled. 
	 */
	public int getMonth()
	{
		final GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTime(this.date); 
		return gc.get(GregorianCalendar.MONTH) + 1; 
	}
	
	/**
	 * @return The year in which this task was scheduled. 
	 */
	public int getYear()
	{
		final GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTime(this.date); 
		return gc.get(GregorianCalendar.YEAR); 
	}

	/**
	 * @return the taskId
	 */
	public Long getTaskId() 
	{
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(Long taskId) 
	{
		this.taskId = taskId;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) 
	{
		this.date = date;
	}

	/**
	 * @param descritpion the description to set
	 */
	public void setDescritpion(String descritpion) 
	{
		this.description = descritpion;
	}

	/**
	 * @param effort the effort to set
	 */
	public void setEffort(double effort) 
	{
		this.effort = effort;
	}

	/**
	 * @return the id
	 */
	public Long getId() 
	{
		return this.taskId;
	}

	/**
	 * @return the date
	 */
	public Date getDate() 
	{
		return this.date;
	}

	/**
	 * @return the description
	 */
	public String getDescription() 
	{
		return this.description;
	}

	/**
	 * @return the effort
	 */
	public double getEffort() 
	{
		return this.effort;
	}

	/**
	 * @return the payer
	 */
	public String getPayer() 
	{
		return payer;
	}

	/**
	 * @param payer the payer to set
	 */
	public void setPayer(String payer) 
	{
		this.payer = payer;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	/**
	 * @return The date on which this task is supposed to be executed in 
	 * format yyyy-MM-dd
	 */
	public String getDateString()
	{
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		return sdf.format(this.date);  
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Task [TaskId=" + taskId + ", date=" + date
				+ ", descritpion=" + description + ", effort=" + effort + ", payer="+payer+"]";
	} 
	
	
}
