package com.chrisgaona.interactivestory.model;

/**
 * Created by chrisgaona on 8/22/17.
 */

public class Choice {
    private int textId;
    private int nextPage;

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}