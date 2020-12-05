package com.java;


import java.util.*;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		InvertedIndex ii=new InvertedIndex();
		
		ii.readFile();
		
		
		
		
		Iterator<String> inorder = ii.wordDictionary.getKeyIterator();
		
		String str = inorder.next();
		
		while(inorder.hasNext())
		{
			str = inorder.next();
			System.out.println(str + " "+ ii.wordDictionary.getValue(str).getString()) ;
		}
		
		
		
		
		ii.getWord();
		
		
		
	}

}
