package com.java;
import java.io.*;
import java.util.*;

public class InvertedIndex {
	public DictionaryInterface<String, ListWithIteratorInterface<Integer>> wordDictionary;
	private static Integer idNum;
	public int results = 0;
	public int retrievalTime = 0;
	ListWithIteratorInterface<Document> docList;
	public InvertedIndex() {
		wordDictionary = new Dictionary<>();
		docList = new LinkedListWithIterator<>();
	}

	public void readFile() {
		String fileName;
		String str;


		for (int i = 1; i < 20; i++) {
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

			public void documentMatch()
			{
				//get terms
				Scanner scanner=new Scanner(System.in);
				String term;
				String str;
				ListWithIteratorInterface<Document> docList = new LinkedListWithIterator<>();
				System.out.println("Enter the terms separated by space ");
				term=scanner.nextLine();
				String[] inArr=term.split(" ");

				for(int i=0;i<inArr.length;i++)
				{
					str=tokenize(inArr[i]);

					if(stopList(str))
					{
						if(wordDictionary.contains(str)){
							//get docId's and docFrequency, add to docList
							matchScore(str);
						}
					}

				}
				System.out.println(results + " results " + "(" + retrievalTime + " seconds)");
			}

			public void matchScore(String str){
				Iterator<String> keyIterator = wordDictionary.getKeyIterator();
				Iterator<ListWithIteratorInterface<Integer>> valueIterator = wordDictionary.getValueIterator();
				ListWithIteratorInterface<Integer> idList = valueIterator.next();
				String current = keyIterator.next();
				while (keyIterator.hasNext()){
					if(str.equals(current)){
						Iterator<Integer> listIterator = idList.getIterator();
						while (listIterator.hasNext()){
							int docID = listIterator.next();
							Document temp = new Document(docID);
							int freq = listIterator.next();
							Iterator<Document> docIterator = docList.getIterator();
								while (docIterator.hasNext()){
									Document curDoc = docIterator.next();
									if(curDoc.docID == docID){
										int prfq = curDoc.docFq;
										int newfq = prfq + freq;
										curDoc.setDocFq(newfq);
										System.out.println("Document Id: "+ curDoc.docID + " Match Score: " + curDoc.getDocFq());
									}
								}
								temp.setDocFq(freq);
								docList.add(temp);
								System.out.println("Documetn Id: " + docID + " Match Score: " + freq);

						}
						}
					idList = valueIterator.next();
					current = keyIterator.next();
					}
				results = docList.getLength();
				}

			//display entire dictionary
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
