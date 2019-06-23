package com.cqupt.mapper;

import com.cqupt.pojo.AdminStudent;
import com.cqupt.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    public String getPasswordById(String id);
    public List<Student> getStudents();
    public List<AdminStudent> getStudents2();
    public void updateStudent(Student student);
    public void deleteStudent(String id);
    public void addStudent(Student student);
    public List<Student> searchStudentByKeyWord(String keyWord);
    public String getStudentIdByIdOrName(String student);
    public List<AdminStudent> getStudentByKeyWord(String keyWord);
}
