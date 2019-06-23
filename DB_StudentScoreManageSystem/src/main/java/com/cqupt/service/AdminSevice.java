package com.cqupt.service;

import com.cqupt.pojo.*;

import java.util.List;

public interface AdminSevice {
    public List<Student> getStudents();
    public List<Teacher> getTeachers();
    public List<Course> getCourses();
    public List<AdminStudent> getAdminStudents();
    public void updateStudent(String stuId,String name,String password);
    public void deleteStudent(String id);
    public void addStudent(String id,String name,String password);
    public List<Student> getStudentsByKeyWord(String keyWord);
    public void addTeacher(String id,String name,String password);
    public void updateTeacher(String id,String name,String password);
    public void deleteTeacher(String id);
    public List<Teacher> searchTeacherByKeyWord(String keyWord);
    public void addCourse(String couserId,String courseName);
    public void deleteCourse(String id);
    public void updateCourse(String id,String name);
    public List<Course> getCourseByKeyWord(String keyWord);
    public void addClasses(String id,String teacher,String course);
    public void deleteClassById(String id);
    public void updateClasses(String classId,String teacherId);
    public List<Course> getClassesByKeyWord(String keyWord);
    public void addChoose(String student,String classes);
    public List<AdminStudent> getChooseByKeyWord(String keyWord);
    public void deleteChoose(String studentId,String classId);
    public void updateChoose(String stuId,String classId,String oldNum);
}
