package com.example.javacourseexercise;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by BITshaw on 2018/1/30.
 * 用于维护当前周
 */

public class WeekCount {
    private static int first_week = 1;

    /**
     * 安装APP时调用，设置起始周
     */
    public static void init(){
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);             // 设置每周的第一天为星期一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);     // 每周从周一开始
        cal.setMinimalDaysInFirstWeek(7);                   // 设置每周最少为7天
        cal.setTime(new Date());
        int weeks=cal.get(Calendar.WEEK_OF_YEAR);
        first_week = weeks -12;

    }

    /**
     * 重设当前周
     */
    public static void reset(int current_week){
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);             // 设置每周的第一天为星期一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);     // 每周从周一开始
        cal.setMinimalDaysInFirstWeek(7);                   // 设置每周最少为7天
        cal.setTime(new Date());
        int weeks=cal.get(Calendar.WEEK_OF_YEAR);
        first_week = weeks - current_week;
    }

    /**
     * 获取当前周
     */
    public static int getCurrentWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);             // 设置每周的第一天为星期一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);     // 每周从周一开始
        cal.setMinimalDaysInFirstWeek(7);                   // 设置每周最少为7天
        cal.setTime(new Date());
        int weeks=cal.get(Calendar.WEEK_OF_YEAR);           //得到当前周
        return weeks - first_week;
    }

    public static int getCurrenDay(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK)-1;
    }
}
