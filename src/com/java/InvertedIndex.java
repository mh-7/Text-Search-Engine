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
    //Can take in a word and return it without all the Punctuation and makes
    //it all into lower case.
    private String tokenize(String str)
    {
    	String str1 = "";
    	String withOutPunc = str.replaceAll("[^a-zA-Z ]", "");
    	withOutPunc = withOutPunc.toLowerCase();
    	return withOutPunc;
    }
    
    private boolean stopList(String str)
    {
    	tokenize(str);
    	boolean boo = true;
    	String str1;
    	
    	try
        {
			   File file = new File ("stop-list.txt");
	           Scanner scan = new Scanner(file);
	           while(scan.hasNext())
	           {
	        	   str1 = scan.next();
	        	   if(str == str1)
	        		   boo = false;
	           }
        }
		catch(IOException io)
        {
            System.out.println("The file not found");
        }
    	return boo;
    }
}
