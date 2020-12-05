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


		for (int i = 1; i < 3; i++) {
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
								idList.add(0);
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

			public void searchTerm(){
				Scanner scan = new Scanner(System.in);

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
