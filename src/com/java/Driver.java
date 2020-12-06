package com.java;


import java.util.*;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean cont=true;
		String str;
		Scanner input=new Scanner(System.in);
		
		InvertedIndex ii=new InvertedIndex();
		ii.readFile();

		while(cont)
		{
			ii.getWord();
			System.out.println("\nDo you want to continue? yes/YES ,anything else will quit the program");
			str=input.nextLine();
			if(str.equals("yes") || str.equals("YES"))
				cont=true;
			else
				cont=false;
			
		}

		System.out.println("\nEnd");

	
	}

}
