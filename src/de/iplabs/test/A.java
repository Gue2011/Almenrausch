package de.iplabs.test;

// WAS GIBT DAS PROGRAMM AUS?
public class A 
{
	public static void main (String [] xxx)
	{
		doSmth();
		System.gc();  
	}
	
	public static void doSmth()
	{
		final A a = new A (); 
	}
	
	public void finalize()
	{
		System.out.println("Finalizing A ..."); 	
	}	

}
