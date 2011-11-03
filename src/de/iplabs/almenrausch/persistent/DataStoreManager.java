package de.iplabs.almenrausch.persistent;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class DataStoreManager {
	
	private static final PersistenceManagerFactory factory = JDOHelper.getPersistenceManagerFactory("transactions-optional"); 
	
	private DataStoreManager() {}
	
	public static PersistenceManagerFactory get() {
		return factory; 
	}
}
