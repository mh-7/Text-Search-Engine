package com.java;

public interface listInterface<T>
{
    public void add(T newEntry);
    public void add(int newPosition,  T newEntry);
    public T remove(int givenPosition);
    public void clear();
    public T replace(int givenPosition, T newEntry);
    public T getEntry(int givePosition);
    public T[] toArray();
    public boolean contains(T anEntry);
    public int getLength();
    public boolean isEmpty();
}
