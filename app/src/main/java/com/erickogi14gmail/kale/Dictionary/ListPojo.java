package com.erickogi14gmail.kale.Dictionary;

/**
 * Created by Eric on 10/17/2017.
 */

public class ListPojo {


    private int entry_id;
    private String  word_english;
    private String  word_kale;
    private String  word_english_description;
    private String word_kale_description;

    public int getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public String getWord_english() {
        return word_english;
    }

    public void setWord_english(String word_english) {
        this.word_english = word_english;
    }

    public String getWord_kale() {
        return word_kale;
    }

    public void setWord_kale(String word_kale) {
        this.word_kale = word_kale;
    }

    public String getWord_english_description() {
        return word_english_description;
    }

    public void setWord_english_description(String word_english_description) {
        this.word_english_description = word_english_description;
    }

    public String getWord_kale_description() {
        return word_kale_description;
    }

    public void setWord_kale_description(String word_kale_description) {
        this.word_kale_description = word_kale_description;
    }
}
