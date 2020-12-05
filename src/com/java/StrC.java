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
	    
	    public void addString(String str1)
	    {
	        this.str += " "+count + ", " + str1;
	    }
	    public void tally()
	    {
	        count++;
	    }
	    
	    public void reset()
	    {
	    	count = 1;
	    }
	    public int getCount()
	    {
	        return count;
	    }
	    public String getString()
	    {
	        return str + " " + count;
	    }
	    public boolean sameId(int idNum)
	    {
	    	String[] arr = str.split(", ");
	    	for(int i = 0; i < arr.length; i++)
	    	{
	    		if(arr[i].equals("" + idNum))
	    			return true;
	    	}
	    	return false;
	    }
	    
	}
