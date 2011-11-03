package de.iplabs.almenrausch.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class MantisTask 
{
	
	@PrimaryKey  
	private Long taskId; 
	@Persistent
	private Date date; 
	@Persistent
	private String description; 
	@Persistent
	private double effort;
	@Persistent
	private String payer; 

	
	/**
	 * @param id
	 * @param date
	 * @param descritpion
	 * @param effort
	 */
	public MantisTask(final Long taskId, final Date date, final String descritpion,final  double effort, final String payer) {

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
	
	public int getCalenderWeek()
	{
		GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTime(this.date); 
		return gc.get(GregorianCalendar.WEEK_OF_YEAR) - 1; 
	}
	

	/**
	 * @return the taskId
	 */
	public Long getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}



	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param descritpion the descritpion to set
	 */
	public void setDescritpion(String descritpion) {
		this.description = descritpion;
	}

	/**
	 * @param effort the effort to set
	 */
	public void setEffort(double effort) {
		this.effort = effort;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.taskId;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * @return the descritpion
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the effort
	 */
	public double getEffort() {
		return this.effort;
	}

	/**
	 * @return the payer
	 */
	public String getPayer() {
		return payer;
	}

	/**
	 * @param payer the payer to set
	 */
	public void setPayer(String payer) {
		this.payer = payer;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDateString()
	{
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		return sdf.format(this.date);  
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Task [TaskId=" + taskId + ", date=" + date
				+ ", descritpion=" + description + ", effort=" + effort + ", payer="+payer+"]";
	} 
	
	
}
