package de.iplabs.almenrausch.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.appengine.repackaged.com.google.common.collect.ImmutableList;

/**
 * @author gue
 */
public class SessionTest 
{
	/***/
	private Session session; 
	
	@Before
	public void createSession()
	{
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class); 
		HttpSession session = Mockito.mock(HttpSession.class); 
		Mockito.when(session.getAttribute("session")).thenReturn(null); 
		Mockito.when(request.getSession()).thenReturn(session); 
		
		this.session = Session.getCurrentSession(request); 
	}
	
	/***/
	@Test
	public void testGetMšnigTesks()
	{
		MantisTask t1 = Mockito.mock(MantisTask.class); 
		Mockito.when(t1.getPayer()).thenReturn("Direkt"); 

		MantisTask t2 = Mockito.mock(MantisTask.class); 
		Mockito.when(t2.getPayer()).thenReturn("Fuji"); 

		MantisTask t3 = Mockito.mock(MantisTask.class); 
		Mockito.when(t3.getPayer()).thenReturn("Fuji"); 
		
		List<MantisTask> tasks = ImmutableList.of(t1, t2, t3); 
		
		this.session.setCurrentTasks(tasks); 
		Assert.assertEquals(2, this.session.getCurrentMoenigTasks().size()); 
		Assert.assertEquals(1, this.session.getCurrentDirectTasks().size()); 
	}
	
}
