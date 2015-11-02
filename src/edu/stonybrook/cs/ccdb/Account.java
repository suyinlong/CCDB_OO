package edu.stonybrook.cs.ccdb;
 
public class Account {
    private String number;
    private int balance;
    private int limit;
    
	public String GetNumber()
	{
		return number;
	}
	
	public void SetNumber( String number )
	{
		this.number = number;
	}
    
	public int GetBalance()
	{
		return balance;
	}
	
	public void SetBalance( int balance )
	{
		this.balance = balance;
	}
    
	public int GetLimit()
	{
		return limit;
	}
	
	public void SetLimit( int limit )
	{
		this.limit = limit;
	}
}
