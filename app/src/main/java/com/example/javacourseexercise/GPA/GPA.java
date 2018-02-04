package com.example.javacourseexercise.GPA;

/**
 * Created by Liao on 2018/1/31.
 */
public class GPA {
    private String name;
    private int imageId;
    private String totalScore;

    public GPA(String name, int imageId, String totalScore){
        this.name=name;
        this.imageId=imageId;
        this.totalScore=totalScore;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }
}
