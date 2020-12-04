package com.java;
import java.io.*;
import java.util.*;

public class InvertedIndex {
    public DictionaryInterface<String, ListInterface<Integer>> wordDictionary;
    private static int idNum;

    public InvertedIndex() {
    	wordDictionary = new Dictionary<>();
    	
    }
    public void readFile(){
    	String fileName;
    	String str;
    	
    	
    	for(int i=1;i<2;i++)
    	{
    		idNum=1;
    		fileName="Text-" +i+".txt";
    		
    		try { //src/com/java/collection/Text-1.txt
            {
    			   File file = new File ("src\\com\\java\\collection\\"+fileName);

    	           Scanner scan = new Scanner(file);
    	           
    	           while(scan.hasNext())
    	           {
    	        	   str=scan.next();
    	        	   //here we can call Tokenize method or anything else
    	        	   str = tokenize(str);
    	       
    	        	   if(stopList(str) && !wordDictionary.contains(str))
    	        	   {
  
    	        		   ListInterface<Integer> idList=new LList<>();
    	        		   wordDictionary.add(str, idList);
    	        		   
    	        	   }
    	   
    	    			
    	           }
            }
    		catch(IOException io)
            {
                System.out.println("The file not found");
            }
    	}

//    	}
    	
    	

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
    	boolean boo = true;
    	String str1;
    	
    	try
        {
			   File file = new File ("src\\com\\java\\stop-list.txt");
	           Scanner scan = new Scanner(file);
	           while(scan.hasNext())
	           {
	        	   str1 = scan.next();
	        	   if(str.equals(str1))
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
