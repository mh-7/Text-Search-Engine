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
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator getKeyIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator getValueIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
