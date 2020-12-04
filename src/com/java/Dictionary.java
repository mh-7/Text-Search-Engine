package com.java;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Dictionary<K extends Comparable<? super K>, V> 
			implements DictionaryInterface<K, V>
{
	private Node firstNode;
	private int  numberOfEntries;

	public Dictionary()
	{
		initializeDataFields();
	}
	
	public V add(K key, V value) 
	{
		V result = null;
		if ((key == null) || (value == null))
			throw new IllegalArgumentException("Cannot add null to a dictionary.");
		else
		{
			Node currentNode = firstNode;
			Node nodeBefore = null;
			while ((currentNode != null) && key.compareTo(currentNode.getKey()) > 0)
			{
				nodeBefore = currentNode;
				currentNode = currentNode.getNextNode();
			} // end while

			if ( (currentNode != null) && key.equals(currentNode.getKey()) )
			{
				result = currentNode.getValue();
				currentNode.setValue(value);
			}
			else
			{
				Node newNode = new Node(key, value);
				if (nodeBefore == null)
				{
					newNode.setNextNode(firstNode); 
					firstNode = newNode;
				}
				else
				{
					newNode.setNextNode(currentNode);
					nodeBefore.setNextNode(newNode);
				}
				numberOfEntries++;
			}
		}
		return result;		
	}

	@Override
	public V remove(K key) 
	{
		V result = null;
		if (key == null)
			throw new IllegalArgumentException("Key cannot be null.");
		else 
		{
			Node currentNode = firstNode;
			Node nodeBefore = null;
			if (key.equals(firstNode.getKey()))
			{
				result = firstNode.getValue();
				firstNode = firstNode.getNextNode();
				return result;
			}			
			else
			{
				while ((currentNode != null) && key.compareTo(currentNode.getKey()) > 0)
				{
					nodeBefore = currentNode;
					currentNode = currentNode.getNextNode();
				}
				
				if ( (currentNode != null) && key.equals(currentNode.getKey()) )
				{
					result = currentNode.getValue();
					Node nodeToRemove = nodeBefore.getNextNode();
					Node nodeAfter = nodeToRemove.getNextNode();
					nodeBefore.setNextNode(nodeAfter);
					nodeToRemove = null;
				}
				return result;
			}
		}
	}

	@Override
	public V getValue(K key) {
		// TODO Auto-generated method stub
		
		V result = null;
		if(key == null)
			throw new IllegalArgumentException("The key is null");
		else
		{
			Node currentNode = this.firstNode;
		    while (currentNode != null)
		    {
		        if(currentNode.getKey().equals(key))
		        {
		        	result = currentNode.getValue();
		        }
		        	
		        currentNode = currentNode.next;
		    }
			return result;
	
		}
	    
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		if(key == null)
			return false;
	    Node currentNode = this.firstNode;
	    while (currentNode != null)
	    {
	        if(currentNode.getKey().equals(key))
	        	return true;
	        currentNode = currentNode.next;
	    }
		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return new keyIterator();
	}

	@Override
	public Iterator getValueIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(firstNode == null || numberOfEntries == 0)
			return true;
		else
			return false;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return numberOfEntries;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		firstNode = null;
		numberOfEntries = 0;
	}

	private void initializeDataFields(){
		firstNode = null;
		numberOfEntries = 0;
	}
	

	public class keyIterator implements Iterator<K>
	   {
	      private Node nextNode;
	      
	      private keyIterator()
	      {
	         nextNode = firstNode;
	      } // end default constructor
	      
	      public K next()
	      {
	         K result;
	         if (hasNext())
	         {
	            result = nextNode.getKey();
	            nextNode = nextNode.getNextNode(); // Advance iterator
	         }
	         else
	            throw new NoSuchElementException("Illegal call to next(); " +
	                                             "iterator is after end of list.");
	         return result; // Return next entry in iteration
	      } // end next

	      public boolean hasNext()
	      {
	         return nextNode != null;
	      } // end hasNext

	      public void remove()
	      {
	         
	    	  throw new UnsupportedOperationException("remove() is not supported " +
	                                                 "by this iterator");
	      } // end remove
	   } // end IteratorForLinkedList
	
	private class Node{
		private K    Key; // Key
		private V 	Value; // Value
		private Node next; // Link to next node

		private Node(K key)
		{
			Key = key;
			next = null;
		} // end constructor

		private Node(K key, V value)
		{
			Key = key;
			Value = value;
			next = null;
		} // end constructor

		private K getKey(){
			return Key;
		}
		private void setKey(K key){
			Key = key;
		}

		private V getValue(){
			return Value;
		}
		private void setValue(V value){
			Value = value;
		}

		private Node getNextNode()
		{
			return next;
		}
		private void setNextNode(Node nextNode)
		{
			next = nextNode;
		}

	}

}
