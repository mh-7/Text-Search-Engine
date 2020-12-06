package com.java;

import java.util.Iterator;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		InvertedIndex ii=new InvertedIndex();
		
		ii.readFile();

		ii.display();

		//ii.getKey("accept");
		//ii.getKey("able");
		System.out.println();
		//ii.getTerm();

	}

}
