package com.chrisgaona.interactivestory.model;

/**
 * Created by chrisgaona on 8/22/17.
 */

public class Page {
    private int imageId;
    private int textId;
    private Choice mChoice1;
    private Choice mChoice2;
    private boolean isFinalPage = false;

    public Page(int imageId, int textId) {
        this.imageId = imageId;
        this.textId = textId;
        this.isFinalPage = true;
    }

    public Page(int imageId, int textId, Choice choice1, Choice choice2) {
        this.imageId = imageId;
        this.textId = textId;
        mChoice1 = choice1;
        mChoice2 = choice2;
    }

    public boolean isFinalPage() {
        return isFinalPage;
    }

    public void setFinalPage(boolean finalPage) {
        isFinalPage = finalPage;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public Choice getChoice1() {
        return mChoice1;
    }

    public void setChoice1(Choice choice1) {
        mChoice1 = choice1;
    }

    public Choice getChoice2() {
        return mChoice2;
    }

    public void setChoice2(Choice choice2) {
        mChoice2 = choice2;
    }
}
