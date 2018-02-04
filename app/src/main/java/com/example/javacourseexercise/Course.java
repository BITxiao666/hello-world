package com.example.javacourseexercise;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by BITshaw on 2018/1/28.
 * 课程逻辑类
 * 保存每一堂的学习
 * 提供课程信息的存取方法
 * 与数据库Course表对应
 */

public class Course extends DataSupport{
    private int id;
    private String course_name;
    private String tutor_name;
    private int begin_week;
    private int finish_week;
    private String site;
    private int time;

    /**
     * 动态输入检查，由EditActivity调用
     * 检查新课程是否与现有课程冲突
     * @return
     * 1: 冲突
     * 0: 兼容
     */
    public int checkInput() {
        List<Course> list = DataSupport.where("time = ?  ",
                String.valueOf(time)).find(Course.class);
        int i;
        for (i=0;i<list.size();i++){
            if(id!=list.get(i).getId()) {
                if (!((begin_week < list.get(i).begin_week && finish_week < list.get(i).finish_week) ||
                        (begin_week > list.get(i).begin_week && finish_week > list.get(i).finish_week))) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * 获取每个时间段的课程状态
     * 是否有课，是否在当前周
     * @return
     * 0: 没有课
     * 1: 有课，但不是当前周的课
     * 2: 有课，且在当前周
     */
    public static int getStatus(int time,int week){
        List<Course> list = DataSupport.where("time = ?  ",
                String.valueOf(time)).find(Course.class);
        if(list.size()<1){
            return 0;
        }
        int i;
        for (i=0;i<list.size();i++){
            if(week>=list.get(i).getBegin_week()&&week<=list.get(i).getFinish_week()){
                return 1;
            }
        }
        return 2;
    }

    /**
     * 用于当前周课程信息的获取
     * 以及课程详细信息查询操作
     * @return
     */
    public static Course getCourseWithWeek(int time,int week){
        List<Course> list = DataSupport.where("time = ?  ",
                String.valueOf(time)).find(Course.class);
        int i;
        for (i=0;i<list.size();i++){
            if(week>=list.get(i).getBegin_week()&&week<=list.get(i).getFinish_week()){
                return list.get(i);
            }
        }
        return null;
    }

    /**
     * 用于当非前周课程信息的获取
     * @return
     */
    public static Course getCourseNoWeek(int time){
        List<Course> list = DataSupport.where("time = ?  ",
                String.valueOf(time)).find(Course.class);
        return list.get(0);
    }

    public Course(String _course_name, String _tutor_name, String _site,
                  int _begin_week, int _finish_week, int _time) {
        course_name = _course_name;
        tutor_name = _tutor_name;
        site = _site;
        begin_week = _begin_week;
        finish_week = _finish_week;
        time = _time;
    }

    public static void courseClear() {
        List<Course> list = DataSupport.findAll(Course.class);
        int i;
        for (i=0;i<list.size();i++){
            if (list.get(i).getTime()<60){
                DataSupport.delete(Course.class,list.get(i).getId());
            }
        }
    }


    public Course(int _id, String _course_name, String _tutor_name, String _site,
                  int _begin_week, int _finish_week, int _time) {
        id = _id;
        course_name = _course_name;
        tutor_name = _tutor_name;
        site = _site;
        begin_week = _begin_week;
        finish_week = _finish_week;
        time = _time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getBegin_week() {
        return begin_week;
    }

    public int getFinish_week() {
        return finish_week;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public void setBegin_week(int begin_week) {
        this.begin_week = begin_week;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setFinish_week(int finish_week) {
        this.finish_week = finish_week;
    }

    public void setTutor_name(String tutor_name) {
        this.tutor_name = tutor_name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
