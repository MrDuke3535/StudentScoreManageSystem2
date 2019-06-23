package com.cqupt.mapper;

import com.cqupt.pojo.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    public String getPasswordById(String id);
    public List<Teacher> getTeachers();
    public void addTeacher(Teacher teacher);
    public void updateTeacher(Teacher teacher);
    public void deleteTeacher(String id);
    public List<Teacher> searchTeacherByKeyWord(String keyWord);
    public String getIdByIdOrName(String teacher);
}
