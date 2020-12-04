package com.java;

public class StrC {
	    String str;
	    int count;
	    public StrC()
	    {
	        str = "";
	        count =0;
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
