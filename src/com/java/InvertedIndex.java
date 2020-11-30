package com.java;
import java.io.*;
import java.util.*;

public class InvertedIndex {
    private DictionaryInterface<String, LList<Integer>> wordDictionary;

    public InvertedIndex() {
    }
    public void readFile(){
    	
    	String fileName;
    	String str;
    	
    	for(int i=1;i<401;i++)
    	{
    		fileName="Text-" +i+".txt";
    		
    		try
            {
    			   File file = new File (fileName);
    	           Scanner scan = new Scanner(file);
    	           
    	           while(scan.hasNext())
    	           {
    	        	   str=scan.next();
    	        	   
    	        	   //here we can call Tokenize method or anything else
    	    			
    	           }
            }
    		catch(IOException io)
            {
                System.out.println("The file not found");
            }

    	}
    	
    	

    }
}
