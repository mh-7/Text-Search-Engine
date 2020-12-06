package com.java;

public class StrB {
    String str;
    int count;
    public StrB()
    {
        str = "";
        count =0;
    }
    public StrB(String str1)
    {
        str = str1;
        count =0;
    }
    public void setString(String str)
    {
        this.str = str;
    }
    public void addCount(int count)
    {
        this.count += count;
    }
    
    public int getCount()
    {
        return count;
    }
    public String getString()
    {
        return str;
    }
}
