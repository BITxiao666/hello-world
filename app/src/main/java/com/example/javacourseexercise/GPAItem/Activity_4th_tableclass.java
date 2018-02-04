package com.example.javacourseexercise.GPAItem;

import org.litepal.crud.DataSupport;

/**
 * Created by Liao on 2018/2/1.
 */

public class Activity_4th_tableclass extends DataSupport{
    private String date;
    private String score;
    private String event;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
