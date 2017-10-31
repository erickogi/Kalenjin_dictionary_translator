package com.erickogi14gmail.kale.Dictionary;

import java.io.Serializable;

/**
 * Created by Eric on 10/17/2017.
 */

public class WordPojo  implements Serializable{
    private int entry_id;
    private String   word_kale;
    private String  word_english;
    private String word_kiswahili;
    private String word_english_description;
    private String word_kale_description;
    private int word_view_count;
    private String word_view_last;
    private String word_favourite_status;
    private String word_others;

    public WordPojo(int entry_id, String word_kale, String word_english, String word_kiswahili, String word_english_description, String word_kale_description, int word_view_count, String word_view_last, String word_favourite_status, String word_others) {
        this.entry_id = entry_id;
        this.word_kale = word_kale;
        this.word_english = word_english;
        this.word_kiswahili = word_kiswahili;
        this.word_english_description = word_english_description;
        this.word_kale_description = word_kale_description;
        this.word_view_count = word_view_count;
        this.word_view_last = word_view_last;
        this.word_favourite_status = word_favourite_status;
        this.word_others = word_others;
    }

    public WordPojo() {

    }


    public int getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public String getWord_kale() {
        return word_kale;
    }

    public void setWord_kale(String word_kale) {
        this.word_kale = word_kale;
    }

    public String getWord_english() {
        return word_english;
    }

    public void setWord_english(String word_english) {
        this.word_english = word_english;
    }

    public String getWord_kiswahili() {
        return word_kiswahili;
    }

    public void setWord_kiswahili(String word_kiswahili) {
        this.word_kiswahili = word_kiswahili;
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

    public int getWord_view_count() {
        return word_view_count;
    }

    public void setWord_view_count(int word_view_count) {
        this.word_view_count = word_view_count;
    }

    public String getWord_view_last() {
        return word_view_last;
    }

    public void setWord_view_last(String word_view_last) {
        this.word_view_last = word_view_last;
    }

    public String getWord_favourite_status() {
        return word_favourite_status;
    }

    public void setWord_favourite_status(String word_favourite_status) {
        this.word_favourite_status = word_favourite_status;
    }

    public String getWord_others() {
        return word_others;
    }

    public void setWord_others(String word_others) {
        this.word_others = word_others;
    }
}
