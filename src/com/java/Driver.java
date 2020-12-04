package com.java;

import java.util.Iterator;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		InvertedIndex ii=new InvertedIndex();
		
		ii.readFile();
		
		
		
		Iterator<String> inorder = ii.wordDictionary.getKeyIterator();
		
		
		
		while (inorder.hasNext())
		{	
			String str = inorder.next();
			System.out.print(str + " " + ii.wordDictionary.getValue(str).getCount() +" ");
		} // end while
		
		

	}

}
