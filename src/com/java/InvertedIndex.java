package com.java;
import javax.print.Doc;
import java.io.*;
import java.util.*;

public class InvertedIndex {
	public DictionaryInterface<String, ListWithIteratorInterface<Integer>> wordDictionary;
	private static Integer idNum;
	public int results = 0;
	public double retrievalTime = 0;
	ListWithIteratorInterface<Document> docList;
	long startTime = 0;
	public InvertedIndex() {
		wordDictionary = new Dictionary<>();
		docList = new LinkedListWithIterator<>();
	}

	public void readFile() {
		String fileName;
		String str;


		for (int i = 1; i < 50; i++) {
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
				//make sure Doclist is empty
				docList.clear();
				//clear results
				results = 0;
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
				results();
			}

			public void matchScore(String str){
				//start timer
				startTime = System.nanoTime();
				Iterator<String> keyIterator = wordDictionary.getKeyIterator();
				Iterator<ListWithIteratorInterface<Integer>> valueIterator = wordDictionary.getValueIterator();
				ListWithIteratorInterface<Integer> idList = valueIterator.next();
				String currentKey = keyIterator.next();
				while (keyIterator.hasNext()){
					if(str.equals(currentKey)){
						Iterator<Integer> listIterator = idList.getIterator();
						while (listIterator.hasNext()){
							int docID = listIterator.next();
							int frequency = listIterator.next();
							Document document = new Document(docID);
							Iterator<Document> documentIterator = docList.getIterator();
								while (documentIterator.hasNext()){
									Document currentDocument = documentIterator.next();
									if(currentDocument.docID == docID){
										int oldFrequency = currentDocument.frequency;
										int newFrequency = oldFrequency + frequency;
										currentDocument.setFrequency(newFrequency);
										sortDocList(currentDocument);
									}
								}
								document.setFrequency(frequency);
								sortDocList(document);
						}
						}
					idList = valueIterator.next();
					currentKey = keyIterator.next();
					}
				results = docList.getLength();
				}


			//sort doclist
			public void sortDocList(Document document){
				//move document into order ranked by frequency
				if(docList.isEmpty()){
					docList.add(document);
				}
				Iterator<Document> sortIterator = docList.getIterator();
				int position = 1;
				while (sortIterator.hasNext()){
					Document thisDocument = sortIterator.next();
					if(document.frequency >= thisDocument.frequency){
						docList.add(position, document);
						break;
						//possible bug here, should remove old document, but shouldn't be a problem when indexing all files
					}
					position++;
				}
			}

			//return results
			public void results(){
				//end timer
				Long endtime = System.nanoTime();
				long totalTime = endtime - startTime;
				retrievalTime = (double)totalTime/ 1_000_000_000.0;
				System.out.println(results + " results " + "(" + retrievalTime + " seconds)");
				Iterator<Document> documentIterator = docList.getIterator();
				int count = 0;
				while (documentIterator.hasNext() && count < 10){
					Document document = documentIterator.next();
					int docID = document.docID;
					int frequency = document.frequency;
					System.out.println("Document Id: " + docID + " Match Score: " + frequency);
					count++;
				}
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
