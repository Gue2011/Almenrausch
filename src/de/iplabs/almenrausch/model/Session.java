package de.iplabs.almenrausch.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Session implements Serializable
{
	/**
	 * The UUID.  
	 */
	private static final long serialVersionUID = 4653592975794575821L;
	
	private List<MantisTask> currentTasks;

	/**
	 * @return the currentTasks
	 */
	public List<MantisTask> getCurrentMoenigTasks() 
	{
		final List<MantisTask> moenig = new ArrayList<MantisTask>(); 
		for (MantisTask t : this.currentTasks) 
		{
			if ("Fuji".equals(t.getPayer())) moenig.add(t); 
		}
		return moenig;
	}
	
	public List<MantisTask> getCurrentDirectTasks() 
	{
		final List<MantisTask> direct = new ArrayList<MantisTask>(); 
		for (MantisTask t : this.currentTasks) 
		{
			if ("Direkt".equals(t.getPayer())) direct.add(t); 
		}
		return direct;
	}

	/**
	 * @param currentTasks the currentTasks to set
	 */
	public void setCurrentTasks(final List<MantisTask> currentTasks) {
		if (this.currentTasks == null) this.currentTasks = new ArrayList<MantisTask>(); 
		this.currentTasks.clear(); 
		for (final MantisTask t : currentTasks)
		{
			this.currentTasks.add(t); 
		}
	} 
	
	public int getNumberOfTasks()
	{
		return this.currentTasks.size(); 
	}
	
	public String getSumOfMoenigTasks()
	{
		double sum = 0.0; 
		for (MantisTask t : this.currentTasks) 
		{
			if ("Fuji".equals(t.getPayer())) sum += t.getEffort(); 
		}
		
		return toOutputFormat(sum); 
	}

	public String getSumOfDirectTasks()
	{
		double sum = 0.0; 
		for (MantisTask t : this.currentTasks) 
		{
			if ("Direkt".equals(t.getPayer())) sum += t.getEffort(); 
		}
		
		return toOutputFormat(sum); 
	}
	
	private static String toOutputFormat (final double effort)
	{
		double sumDD = Math.floor(effort/8.0);
		double sumHH = effort - sumDD * 8; 
		
		return sumDD+" Tag(e) "+new BigDecimal(sumHH).setScale(1, BigDecimal.ROUND_HALF_UP)+ " Stunde(n)"; 
	}
	
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
}
