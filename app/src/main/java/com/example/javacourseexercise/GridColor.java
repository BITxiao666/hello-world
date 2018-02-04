package com.example.javacourseexercise;

/**
 * Created by BITshaw on 2018/1/24.
 * 用于向课程格提供颜色
 */

import android.graphics.Color;

public class GridColor {

    public static int getCourseBgColor(int colorFlag) {
        switch (colorFlag) {
            case 0:
                return Color.parseColor("#95e987");
            case 1:
                return Color.parseColor("#00b0ff");
            case 2:
                return Color.parseColor("#00b0ff");
            case 3:
                return Color.parseColor("#f06292");
            case 4:
                return Color.parseColor("#40c4ff");
            case 5:
                return Color.parseColor("#ff5252");
            case 6:
                return Color.parseColor("#7ba3eb");
            case 7:
                return Color.parseColor("#ff6e40");
            case 8:
                return Color.parseColor("#69f0ae");
            case 9:
                return Color.parseColor("#8cc7fe");

            default:
                return Color.GRAY;
        }
    }
}
