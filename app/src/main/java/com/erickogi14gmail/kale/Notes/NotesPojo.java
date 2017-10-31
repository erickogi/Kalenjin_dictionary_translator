package com.erickogi14gmail.kale.Notes;

import java.io.Serializable;

/**
 * Created by Eric on 10/19/2017.
 */

public class NotesPojo implements Serializable {
    private int entry_id;
    private String note_title;
    private String note_content;
    private String note_date;

    public int getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    public String getNote_date() {
        return note_date;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }
}
