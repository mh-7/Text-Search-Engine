package com.java;
import java.io.*;
import java.util.*;

public class InvertedIndex {
	public DictionaryInterface<String, ListWithIteratorInterface<Integer>> wordDictionary;
	private static Integer idNum;

	public InvertedIndex() {
		wordDictionary = new Dictionary<>();

	}

	public void readFile() {
		String fileName;
		String str;


		for (int i = 1; i < 10; i++) {
			idNum = 1000 + i;
			fileName = "Text-" + i + ".txt";
			try {
				{
					File file = new File("src/com/java/collection/" + fileName);

					Scanner scan = new Scanner(file);

					while (scan.hasNext()) {
						str = tokenize(scan.next());
						if(stopList(str)){
							ListWithIteratorInterface<Integer> idList = wordDictionary.getValue(str);
							if(idList==null){
								idList = new LinkedListWithIterator<>();
								wordDictionary.add(str, idList);
								idList.add(idNum);
								idList.add(0);
							}
							if(idList.contains(idNum)){
								int pos = idList.getLength();
								int count = idList.getEntry(pos);
								count++;
								idList.replace(pos, count);
							}
							else {
								idList.add(idNum);
								idList.add(1);
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
					File file = new File("src/com/java/stop-list.txt");
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
				//counts number of documents the term is in
			}

			public void matchMeasure(String [] array){
				String str = "test";
				if(wordDictionary.contains(str)){
					wordDictionary.getValue(str);
				}
			}

			public void documentMatcher(String str){
			//parse string into array of strings

			}


			public void getTerm()
			{
				Scanner scanner=new Scanner(System.in);
				String term;
				String str;

				System.out.println("Enter the terms separated by space ");
				term=scanner.nextLine();
				String[] inArr=term.split(" ");

				for(int i=0;i<inArr.length;i++)
				{
					str=tokenize(inArr[i]);

					if(stopList(str))
					{
						if(wordDictionary.contains(str)){
							//System.out.println(str);
							getKey(str);
							System.out.println();

						}
						//call docMatch method
						//System.out.println(wordDictionary.getValue(str));
					}

				}
			}

			public void getKey(String str){
				Iterator<String> keyIterator = wordDictionary.getKeyIterator();
				Iterator<ListWithIteratorInterface<Integer>> valueIterator = wordDictionary.getValueIterator();
				ListWithIteratorInterface<Integer> idList = valueIterator.next();
				String current = keyIterator.next();
				while (keyIterator.hasNext()){
					if(str.equals(current)){
						System.out.println(current);
						Iterator<Integer> listIterator = idList.getIterator();
						System.out.print("Key: ");
						while (listIterator.hasNext()){
							System.out.print(+ listIterator.next() + " ");
						}
						}
					idList = valueIterator.next();
					current = keyIterator.next();
					}
				}


			public void display(){
			Iterator<String> keyIterator = wordDictionary.getKeyIterator();
			Iterator<ListWithIteratorInterface<Integer>> valueIterator = wordDictionary.getValueIterator();

			while (keyIterator.hasNext()){
				System.out.println("Value: " + keyIterator.next() + " ");
				ListWithIteratorInterface<Integer> idList = valueIterator.next();
				Iterator<Integer> listIterator = idList.getIterator();

				System.out.print("Key: ");
					while (listIterator.hasNext()){
					System.out.print(+ listIterator.next() + " ");
				}
				System.out.println();
			}
			}
}
