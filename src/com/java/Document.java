package com.java;

public class Document {
    int docID = 0;
    int docFq = 0;

    public Document(int docID) {
        this.docID = docID;
    }

    public Document(int docID, int docFq) {
        this.docID = docID;
        this.docFq = docFq;
    }

    public int getDocID() {
        return docID;
    }

    public void setDocID(int docID) {
        this.docID = docID;
    }

    public int getDocFq() {
        return docFq;
    }

    public void setDocFq(int docFq) {
        this.docFq = docFq;
    }
}
