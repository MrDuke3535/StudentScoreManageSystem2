package com.cqupt.service;

import com.cqupt.pojo.Classes;
import com.cqupt.pojo.SeeStudent;
import com.cqupt.pojo.UpdateScore;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    public List<Classes> getClasses(String id);
    public List<SeeStudent> getSeeStudent(String classId);
    public void updateScore(String stuId,String classId,String score);
    public Map<String,Integer> getScoreRate(String classId);
    public List<SeeStudent> getStudentByKeyWord(String classId,String keyWord);
    public String downloadTable(String classId);
}
