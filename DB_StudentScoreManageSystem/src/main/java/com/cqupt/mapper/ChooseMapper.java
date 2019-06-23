package com.cqupt.mapper;

import com.cqupt.pojo.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChooseMapper {
    public List<Choose> queryChooseCourseById(String id);
    public Integer getChooseNumByClassId(String classId);
    public List<SeeStudent> getSeeStudentByClassId(String classId);
    public void updateScore(UpdateScore updateScore);
    public List<AdminClass2> getAdminClass2ByStudent(String stuId);
    public List<SeeStudent> getStudentByKeyWord(Map map);
    public void addChoose(Choose choose);
    public void deleteChoose(Choose choose);
    public void updateChoose(UpdateChoose updateChoose);
    public void updateFeedback(Choose choose);
}
