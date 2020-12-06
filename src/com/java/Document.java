package com.java;

public class Document {
    int docID = 0;
    int frequency = 0;

    public Document(int docID) {
        this.docID = docID;
    }

    public Document(int docID, int frequency) {
        this.docID = docID;
        this.frequency = frequency;
    }

    public int getDocID() {
        return docID;
    }

    public void setDocID(int docID) {
        this.docID = docID;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
