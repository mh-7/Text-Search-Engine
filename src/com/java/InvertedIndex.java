package com.java;
import java.io.*;
import java.util.*;

public class InvertedIndex {
	public DictionaryInterface<String, StrC> wordDictionary;
	private static int idNum;
	private static ArrayList<String> StopList;
	public long startTime;
	public long endTime;
	Double retrievalTime;
	public InvertedIndex() {
		wordDictionary = new Dictionary<>();
		StopList=new ArrayList<>();
		stopListfill();
	}

	public void readFile() {
		String fileName;
		String str;


		for (int i = 1; i < 424; i++) {
			idNum = i;
			fileName = "Text-" + i + ".txt";
			try {
				{
					File file = new File("src/com/java/collection/" + fileName);

					Scanner scan = new Scanner(file);
					

					while (scan.hasNext()) {
						str = scan.next();
						//here we can call Tokenize method or anything else
						str = tokenize(str);

						if (checkStopList(str) && !wordDictionary.contains(str)) {
							StrC string = new StrC("" + idNum);
							wordDictionary.add(str, string);
							
						}
						else if(wordDictionary.contains(str) && checkStopList(str))
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
			
			private void stopListfill()
			{
				String str1;
				try {
					// if using mac change \\ to /
					File file = new File("src/com/java/stop-list.txt");
					Scanner scan = new Scanner(file);
					while (scan.hasNext()) {
						str1 = scan.next();
						StopList.add(str1);
					}
				} catch (IOException io) {
					System.out.println("The file not found");
				}
				
			}
			
			private boolean checkStopList(String str)
			{
				for(int i=0;i<StopList.size();i++)
				{
					if(str.equals(StopList.get(i)))
						return false;

				}
				
				return true;
				
			}
			
			
			public void getWord()
			{
				//reset time and start timer
				startTime = 0;
				startTime = System.nanoTime();
				Scanner input=new Scanner(System.in);
				String inputGet;
				String str;
				ArrayList<String> bArr = new ArrayList<>();
				ArrayList<Integer> termCounter = new ArrayList<>();
				
				
				System.out.println("Enter the terms separated by space: ");
				inputGet=input.nextLine();
				String[] inArr=inputGet.split(" ");
				
				for(int i=0;i<inArr.length;i++)
				{
					str=tokenize(inArr[i]);
					
					if(checkStopList(str))
					{
						str=docMatch(str);
						if(!str.equals("-1"))
						{
							ArrayList<String> temp = match(str);
						
							
							for(int a=0;a<temp.size();a+=2)
							{
								if(!bArr.contains(temp.get(a)))
								{
									bArr.add(temp.get(a));
									termCounter.add(Integer.parseInt(temp.get(a+1)));
								}
								else
								{
									int t=bArr.indexOf(temp.get(a));
									int num= (termCounter.get(t)) + Integer.parseInt(temp.get(a+1));   
									termCounter.set(t, num);
								}
								
							}	
							
						}
					}
				}
				test(bArr,termCounter);
			}
			
			public void test(ArrayList<String> bArr, ArrayList<Integer> termCounter)
			{
				//end timer
				endTime = System.nanoTime();
				retrievalTime = (double)endTime-startTime/1_000_000_000.0;
				insertionSort(termCounter, bArr); 
				
				System.out.println("\nNumber of documents with at least one match: " + bArr.size() + " (" + retrievalTime + " seconds)");
				System.out.println("\nTop matched documents are:");
				
				int count=1;
				
				for(int i=bArr.size()-1;i>bArr.size()-11;i--)
				{
					if(i>=0)
					{
						System.out.println(count +". Document "+ bArr.get(i) + " with score : " + termCounter.get(i));
						count++;
					}
				}

			}
			
			
			
			
			public String docMatch(String str)
			{
				
				if(wordDictionary.contains(str))
				{
					return wordDictionary.getValue(str).getString();
				}
				
				return "-1";	
			}
			
			
			public ArrayList<String> match(String str)
			{
	
				ArrayList<String> temp = new ArrayList<>();
	
				String[] t=str.split(", ");
		
				for(int i=0;i<t.length;i++)
				{
					String[] t2=t[i].split(" ");
					temp.add(t2[0]);
					temp.add(t2[1]);
				}
				return temp;	
			}
			
			
			
			public static void insertionSort(ArrayList<Integer> termCounter,ArrayList<String> bArr) 
			{
				for (int i = 1; i < termCounter.size(); i++) {
					  
					  int currentElement = termCounter.get(i);
					  String temp = bArr.get(i);
					  int k;
					  for (k = i - 1; k >= 0 && termCounter.get(k) > currentElement; k--)
					  {
						  
						  
						  termCounter.set(k+1, termCounter.get(k));
						  bArr.set(k+1, bArr.get(k));
					  }
					    
					  termCounter.set(k+1,currentElement);
					  bArr.set(k+1,temp);
				}
					 
			}
			
			
			
}
