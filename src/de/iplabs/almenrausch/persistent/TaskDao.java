package de.iplabs.almenrausch.persistent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;


import de.iplabs.almenrausch.model.MantisTask;

public class TaskDao {
	
	private final PersistenceManager pm = DataStoreManager.get().getPersistenceManager(); 
	
	private static final Logger log = Logger.getLogger(TaskDao.class.getName()); 
	
	public void save (final MantisTask task) 
	{
		try 
		{
			final String query = "select from " + MantisTask.class.getName();
			@SuppressWarnings("unchecked")
			List<MantisTask> tasks = (List<MantisTask>) this.pm.newQuery(query).execute();
			log.info("Tasks in db: "+tasks.size()); 
		
			for (final MantisTask t : tasks)
			{
				log.info("-->>"+t.getCalenderWeek()); 
				if (t.getTaskId().equals(task.getTaskId())) 
				{
					log.info("Task "+task.getTaskId()+" already present in DB. Not stored!"); 
					return; 
				}
			}
			this.pm.makePersistent(task); 
		}
		finally 
		{
			this.pm.flush(); 
			this.pm.close(); 
		}
	}
	
	public void update (final MantisTask task)
	{
		try 
		{
			this.pm.makePersistent(task); 
			this.pm.detachCopy(task); 
		}
		finally 
		{
			this.pm.flush(); 
			this.pm.close(); 
		}
	}
	
	public List<MantisTask> getTasksByCalendarWeek (final int week)
	{
		try 
		{
			final String query = "select from " + MantisTask.class.getName();
			this.pm.refreshAll(); 

			@SuppressWarnings("unchecked")
			List<MantisTask> tasks = (List<MantisTask>) this.pm.newQuery(query).execute();
		
			final List<MantisTask> target = new ArrayList<MantisTask>(); 
			
			for (final MantisTask t : tasks)
			{
				if (t.getCalenderWeek() == week) target.add(t); 
			}
			
			this.pm.detachCopyAll(target); 
			
			return target; 
		}
		finally 
		{
			this.pm.flush(); 
			this.pm.close(); 
		}
	}
	
	public MantisTask getByTaskId(final Long taskId)
	{
		try
		{
			MantisTask t = this.pm.getObjectById(MantisTask.class, taskId); 
			return t; 
		}
		catch (Exception e)
		{
			return null; 
		}
		finally
		{
			this.pm.flush(); 
			this.pm.close(); 
		}
		
	}
	
	public boolean deleteTask (final Long taskId)
	{
		try
		{
			final MantisTask t = this.pm.getObjectById(MantisTask.class, taskId); 
			if (t == null) return false; 
			pm.deletePersistent(t);
			
			return true; 
		}
		finally
		{
			this.pm.flush(); 
			this.pm.close(); 
		}
	}
}
