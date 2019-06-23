package com.cqupt.pojo;

public class UpdateScore {
    private String stuId;
    private String classId;
    private int score;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "UpdateScore{" +
                "stuId='" + stuId + '\'' +
                ", classId='" + classId + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
