package com.example.javacourseexercise.GPAItem;
/**
 * Created by Liao on 2018/1/26.
 */


//定义第一个活动的对应的BecyleView的Item对应的类

public class Activity_4th_class {

    private String date;
    private String score;
    private String event;
    public Activity_4th_class(String date,String event,String score){
        this.date=date;
        this.event=event;
        this.score=score;
    }
//    public  Activity_4th_class(String date, String event){
//        this.date=date;
//        this.event=event;
//        this.score= 0;
//    }

    public String getDate(){return date;}
    public String getEvent(){return event;}
    public String  getScore(){return score;}
}