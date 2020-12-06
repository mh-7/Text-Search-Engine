package com.java;
import java.io.*;
import java.util.*;

public class InvertedIndex {
	public DictionaryInterface<String, StrC> wordDictionary;
	private static int idNum;

	public InvertedIndex() {
		wordDictionary = new Dictionary<>();

	}

	public void readFile() {
		String fileName;
		String str;


		for (int i = 1; i < 5; i++) {
			idNum = i;
			fileName = "Text-" + i + ".txt";
			int count = 0;
			try {
				{
					File file = new File("src\\com\\java\\collection\\" + fileName);

					Scanner scan = new Scanner(file);

					while (scan.hasNext()) {
						str = scan.next();
						//here we can call Tokenize method or anything else
						str = tokenize(str);

						if (stopList(str) && !wordDictionary.contains(str)) {
							StrC string = new StrC("" + idNum);
							wordDictionary.add(str, string);
							
						}
						else if(wordDictionary.contains(str) && stopList(str))
						{
							StrC current = wordDictionary.getValue(str);
							if(current.sameId(idNum))
								current.tally();
							else
							{
								current.addString("" + idNum);
								current.reset();
							}
						}


					}
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
			private String tokenize (String str)
			{
				String str1 = "";
				String withOutPunc = str.replaceAll("[^a-zA-Z ]", "");
				withOutPunc = withOutPunc.toLowerCase();
				return withOutPunc;
			}

			private boolean stopList (String str)
			{
				boolean boo = true;
				String str1;

				try {
					File file = new File("src\\com\\java\\stop-list.txt");
					Scanner scan = new Scanner(file);
					while (scan.hasNext()) {
						str1 = scan.next();
						if (str.equals(str1))
							boo = false;
					}
				} catch (IOException io) {
					System.out.println("The file not found");
				}
				return boo;
			}
			
			private int strHelper(StrC str)
			{
				int count = 0;
				String[] strArray = str.getString().split(", ");
				count = strArray.length;
				return count;				
			}
			
			public void getWord()
			{
				Scanner input=new Scanner(System.in);
				String inputGet;
				String str;
				
				System.out.println("\n\nEnter the terms separated by space: ");
				inputGet=input.nextLine();
				String[] inArr=inputGet.split(" ");
				
				for(int i=0;i<inArr.length;i++)
				{
					str=tokenize(inArr[i]);
					
					if(stopList(str))
					{
						str=docMatch(str);
						if(!str.equals(null))
						{
							System.out.println(str);
							
						}
					}
				}
	
			}
			
			
			public String docMatch(String str)
			{
				
				if(wordDictionary.contains(str))
				{
					return wordDictionary.getValue(str).getString();
				}
				
				return null;	
			}
			
			
			public String[] match(String str)
			{
				int tt=0;
				String [] idCount = new String[((str.length()/5)+1)*2];
	
				
				for(int i=0;i<str.length();i+=5)
				{
					idCount[tt]=  "" + str.charAt(i);
					tt++;
					idCount[tt] = "" + str.charAt(i+2);
					tt++;
				}
				
				return idCount;
				
			}
			
			
			
			
			
			
			
}
