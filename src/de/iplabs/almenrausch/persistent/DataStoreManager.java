package de.iplabs.almenrausch.persistent;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Service class to enter {@link PersistenceManagerFactory} in a singleton way. 
 * 
 * @author gue
 */
public class DataStoreManager 
{
	/** The factory managed by this class. **/
	private static final PersistenceManagerFactory factory 
					= JDOHelper.getPersistenceManagerFactory("transactions-optional"); 
	
	/** Hidden constructor. **/
	private DataStoreManager() {}
	
	/** 
	 * Static access on singleton instance. 
	 * @return A factory instance. 
	 */
	public static PersistenceManagerFactory get() 
	{
		return factory; 
	}
}
