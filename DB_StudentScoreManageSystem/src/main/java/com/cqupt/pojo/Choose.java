package com.cqupt.pojo;

public class Choose {
    private String stuId;
    private String name;
    private String course;
    private String classId;
    private String teacher;
    private int score;
    private int feedback;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Choose{" +
                "stuId='" + stuId + '\'' +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", teacher='" + teacher + '\'' +
                ", score=" + score +
                '}';
    }
}
