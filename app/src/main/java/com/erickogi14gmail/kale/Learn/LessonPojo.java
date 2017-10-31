package com.erickogi14gmail.kale.Learn;

import java.io.Serializable;

/**
 * Created by Eric on 10/19/2017.
 */

public class LessonPojo implements Serializable {
    private int progress;
    private String title;
    private String lesson_id;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }
}
