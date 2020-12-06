package com.java;

import java.util.Iterator;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InvertedIndex ii=new InvertedIndex();
		ii.readFile();
		Boolean c = true;
		while(c) {
			ii.documentMatch();
			System.out.println("Type s to search again and q to quit");
			Scanner scanner = new Scanner(System.in);
			String str = scanner.next();
			if(str.equals("q")) {
				c = false;
			}
		}
	}

}
