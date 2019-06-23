package com.cqupt.mapper;

import com.cqupt.pojo.AdminClass;
import com.cqupt.pojo.Classes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesMapper {
    public List<Classes> queryClassById(String id);
    public List<AdminClass> getClassByCourseId(String courseId);
    public void addClasses(Classes classes);
    public void deleteClassesById(String id);
    public void updateClasses(Classes classes);
    public List<AdminClass> getCourseIdByKeyWord(String keyWord);
}
