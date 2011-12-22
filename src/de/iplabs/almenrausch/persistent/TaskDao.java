package de.iplabs.almenrausch.persistent;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import com.google.appengine.repackaged.com.google.common.collect.Collections2;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.inject.Singleton;

import de.iplabs.almenrausch.model.MantisTask;
import de.iplabs.almenrausch.model.timeunit.Month;
import de.iplabs.almenrausch.model.timeunit.Week;
import de.iplabs.almenrausch.model.timeunit.Year;

/**
 * Data access object to access Task database consitstently. 
 * 
 * @author gue
 */
@Singleton
public class TaskDao 
{
	/** The logger. **/
	private static final Logger log = Logger.getLogger(TaskDao.class.getName()); 
	
	/**
	 * Save a task. 
	 * 
	 * @param task The task to be saved. 
	 */
	public void save (final MantisTask task) 
	{
		final PersistenceManager pm = getPersistenceManager(); 
		
		try 
		{
			final String query = "select from " + MantisTask.class.getName();
			@SuppressWarnings("unchecked")
			List<MantisTask> tasks = (List<MantisTask>) pm.newQuery(query).execute();
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
			pm.makePersistent(task); 
		}
		finally 
		{
			pm.flush(); 
			pm.close(); 
		}
	}
	
	/** 
	 * Update a task. 
	 * 
	 * @param task The task to be updated. 
	 */
	public void update (final MantisTask task)
	{
		final PersistenceManager pm = getPersistenceManager(); 
		
		try 
		{
			pm.makePersistent(task); 
			pm.detachCopy(task); 
		}
		finally 
		{
			pm.flush(); 
			pm.close(); 
		}
	}
	
	/**
	 * Get all tasks by calendar week
	 * 
	 * @param week The week for which the tasks are needed. 
	 * @param year The year in which the the needed week is. 
	 * @return A {@link List} of {@link MantisTask}s 
	 */
	public List<MantisTask> getTasksByCalendarWeek (final Week week, final Year year)
	{
		final PersistenceManager pm = getPersistenceManager(); 
		
		try 
		{
			final String query = "select from " + MantisTask.class.getName();
			pm.refreshAll(); 

			@SuppressWarnings("unchecked")
			final List<MantisTask> tasks = (List<MantisTask>) pm.newQuery(query).execute();
		
			final Collection<MantisTask> tc = Collections2.filter(tasks, week.isTaskInThisWeek(year)); 
		
			pm.detachCopyAll(tc); 
			return Lists.newArrayList(tc); 
		}
		finally 
		{
			pm.flush(); 
			pm.close(); 
		}
	}
	
	/**
	 * Get all tasks of a month. 
	 * 
	 * @param month The month for which the tasks are needed. 
	 * @param year The year in which the the needed month is. 
	 * @return A {@link List} of {@link MantisTask}s 
	 */
	public List<MantisTask> getTasksByMonth (final Month month, final Year year)
	{
		final PersistenceManager pm = getPersistenceManager(); 
		
		try 
		{
			final String query = "select from " + MantisTask.class.getName();
			pm.refreshAll(); 

			@SuppressWarnings("unchecked")
			final List<MantisTask> tasks = (List<MantisTask>) pm.newQuery(query).execute();
		
			final Collection<MantisTask> tc = Collections2.filter(tasks, month.isTaskInThisMonth(year)); 
		
			pm.detachCopyAll(tc); 
			return Lists.newArrayList(tc); 
		}
		finally 
		{
			pm.flush(); 
			pm.close(); 
		}
	}
	
	/**
	 * Get a task by its id from database. 
	 * 
	 * @param taskId The task id. 
	 * @return The Task that has got the given id. 
	 */
	public MantisTask getByTaskId(final Long taskId)
	{
		final PersistenceManager pm = getPersistenceManager(); 
		
		try
		{
			MantisTask t = pm.getObjectById(MantisTask.class, taskId); 
			return t; 
		}
		catch (Exception e)
		{
			return null; 
		}
		finally
		{
			pm.flush(); 
			pm.close(); 
		}
	}
	
	/**
	 * Delete a task. 
	 * 
	 * @param taskId The id of the Task to be deleted. 
	 * @return True if deletion was executed, false if object was not found in DB. 
	 */
	public boolean deleteTask (final Long taskId)
	{
		final PersistenceManager pm = getPersistenceManager(); 
		
		try
		{
			final MantisTask t = pm.getObjectById(MantisTask.class, taskId); 
			if (t == null) return false; 
			pm.deletePersistent(t);
			
			return true; 
		}
		finally
		{
			pm.flush(); 
			pm.close(); 
		}
	}
	
	/**
	 * @return A {@link PersistenceManager} instance needed to access DB via JDO. 
	 */
	private PersistenceManager getPersistenceManager()
	{
		final PersistenceManager pm = DataStoreManager.get().getPersistenceManager(); 
		return pm; 
	}
}