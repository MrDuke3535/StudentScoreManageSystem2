package com.cqupt.pojo;

public class AdminClass2 {
    private String classId;
    private String course;
    private String teacher;
    private int score;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "AdminClass2{" +
                "classId='" + classId + '\'' +
                ", course='" + course + '\'' +
                ", teacher='" + teacher + '\'' +
                ", score=" + score +
                '}';
    }
}
