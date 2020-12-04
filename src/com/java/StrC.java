package com.java;

public class StrC {
	    private String str;
	    private int count;
	    
	    public StrC()
	    {
	        str = "";
	        count =1;
	    }
	    public StrC(String str)
	    {
	    	this.str = str;
	    	count = 1;
	    }
	    
	    public void setString(String str)
	    {
	        this.str = str;
	    }
	    public void tally()
	    {
	        count++;
	    }
	    
	    public int getCount()
	    {
	        return count;
	    }
	    public String getString()
	    {
	        return str;
	    }
	}
