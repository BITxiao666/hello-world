package com.example.javacourseexercise.GPAItem;
/**
 * Created by Liao on 2018/1/26.
 */


//定义第一个活动的对应的BecyleView的Item对应的类

public class Activity_1st_class {

    private String date;
    private String score;
    private String event;
    public Activity_1st_class(String date,String event,String score){
        this.date=date;
        this.event=event;
        this.score=score;
    }


    public String getDate(){return date;}
    public String getEvent(){return event;}
    public String  getScore(){return score;}
}