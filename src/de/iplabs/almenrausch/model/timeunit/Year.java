package de.iplabs.almenrausch.model.timeunit;

import java.util.GregorianCalendar;

/**
 * Represents a year type safely: 
 * 
 * @author gue
 */
public enum Year 
{
	_2011 (2011), _2012(2012);

	/** The year number as an int. **/
	private int yearAsInt; 
	
	/**
	 * Constructor. 
	 * 
	 * @param yearAsInt The year number as an int.
	 */
	private Year (int yearAsInt)
	{
		this.yearAsInt = yearAsInt; 
	}
	
	public int asInt()
	{
		return this.yearAsInt; 
	}
	
	public static Year getCurrentYear()
	{
		final GregorianCalendar cal = new GregorianCalendar(); 
		
		int year = cal.get(GregorianCalendar.YEAR);
		if (2011 == year) return Year._2011; 
		else if (2012 == year) return Year._2012; 
		else throw new IllegalArgumentException("The year "+year+" is unknown according to this class."); 
	}
}
