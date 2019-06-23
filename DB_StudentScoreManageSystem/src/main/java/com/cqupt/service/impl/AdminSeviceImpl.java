package com.cqupt.service.impl;

import com.cqupt.mapper.*;
import com.cqupt.pojo.*;
import com.cqupt.service.AdminSevice;
import com.cqupt.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminSeviceImpl implements AdminSevice {

    @Autowired
    private StudentMapper studentMapper = null;
    @Autowired
    private TeacherMapper teacherMapper = null;
    @Autowired
    private CourseMapper courseMapper = null;
    @Autowired
    private ClassesMapper classesMapper = null;
    @Autowired
    private ChooseMapper chooseMapper = null;

    @Override
    public List<Student> getStudents() {
        return studentMapper.getStudents();
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherMapper.getTeachers();
    }

    @Override
    public List<Course> getCourses() {
        List<Course> courses = courseMapper.getCourses();
        for(Course c:courses){
            c.setAdminClasses(classesMapper.getClassByCourseId(c.getId()));
        }
        return courses;
    }

    @Override
    public List<AdminStudent> getAdminStudents() {
        List<AdminStudent> adminStudents = studentMapper.getStudents2();
        for(AdminStudent a:adminStudents){
            a.setAdminClass2s(chooseMapper.getAdminClass2ByStudent(a.getId()));
        }
        return adminStudents;
    }

    @Override
    public void updateStudent(String id, String name, String password) {
        Student student = new Student();
        student.setId(id.trim());
        student.setName(name.trim());
        student.setPassword(password.trim());
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteStudent(String id) {
        studentMapper.deleteStudent(id.trim());
    }

    @Override
    public void addStudent(String id, String name, String password) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setPassword(password);
        studentMapper.addStudent(student);
    }

    @Override
    public List<Student> getStudentsByKeyWord(String keyWord) {
        return studentMapper.searchStudentByKeyWord(keyWord.trim());
    }

    @Override
    public void addTeacher(String id, String name, String password) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName(name);
        teacher.setPassword(password);
        teacherMapper.addTeacher(teacher);
    }

    @Override
    public void updateTeacher(String id, String name, String password) {
        Teacher teacher = new Teacher();
        teacher.setId(id.trim());
        teacher.setName(name.trim());
        teacher.setPassword(password.trim());
        teacherMapper.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacher(String id) {
        teacherMapper.deleteTeacher(id.trim());
    }

    @Override
    public List<Teacher> searchTeacherByKeyWord(String keyWord) {
        return teacherMapper.searchTeacherByKeyWord(keyWord.trim());
    }

    @Override
    public void addCourse(String couserId, String courseName) {
        Course course = new Course();
        course.setId(couserId);
        course.setName(courseName);
        courseMapper.addCourse(course);
    }

    @Override
    public void deleteCourse(String id) {
        courseMapper.deleteCourse(id.trim());
    }

    @Override
    public void updateCourse(String id, String name) {
        Course course = new Course();
        course.setId(id.trim());
        course.setName(name.trim());
        courseMapper.updateCourse(course);
    }

    @Override
    public List<Course> getCourseByKeyWord(String keyWord) {
        return courseMapper.getCourseByKeyWord(keyWord.trim());
    }

    @Override
    public void addClasses(String id, String teacher, String course) {
        String courseId = courseMapper.getIdByIdOrName(course);
        String teacherId = teacherMapper.getIdByIdOrName(teacher);
        if(courseId==null||teacherId==null){

        }else {
            Classes classes = new Classes();
            classes.setClassId(id);
            classes.setCourseId(courseId);
            classes.setTeacher(teacherId);
            classesMapper.addClasses(classes);
        }
    }

    @Override
    public void deleteClassById(String id) {
        classesMapper.deleteClassesById(id.trim());
    }

    @Override
    public void updateClasses(String classId, String teacherId) {
        Classes classes = new Classes();
        classes.setClassId(classId.trim());
        classes.setTeacher(teacherId.trim());
        classesMapper.updateClasses(classes);
    }

    @Override
    public List<Course> getClassesByKeyWord(String keyWord) {
        List<Course> courses = courseMapper.getCourseByKeyWord(keyWord);
        if(courses.size()<=0){
            List<AdminClass> adminClass = classesMapper.getCourseIdByKeyWord(keyWord);
            if(adminClass!=null){
                courses = courseMapper.getCourseByKeyWord(adminClass.get(0).getCourseId());
                courses.get(0).setAdminClasses(adminClass);
            }
        }else {
            for(Course c:courses){
                c.setAdminClasses(classesMapper.getClassByCourseId(c.getId()));
            }
        }
        return courses;
    }

    @Override
    public void addChoose(String student, String classes) {
        String studentId = studentMapper.getStudentIdByIdOrName(student);
        if(studentId!=null){
            Choose choose = new Choose();
            choose.setCourse(classes);
            choose.setStuId(studentId);
            chooseMapper.addChoose(choose);
        }
    }

    @Override
    public List<AdminStudent> getChooseByKeyWord(String keyWord) {
        List<AdminStudent> adminStudents = studentMapper.getStudentByKeyWord(keyWord);
        for(AdminStudent a:adminStudents){
            a.setAdminClass2s(chooseMapper.getAdminClass2ByStudent(a.getId()));
        }
        return adminStudents;
    }

    @Override
    public void deleteChoose(String studentId, String classId) {
        Choose choose = new Choose();
        choose.setStuId(studentId.trim());
        choose.setCourse(classId.trim());
        chooseMapper.deleteChoose(choose);
    }

    @Override
    public void updateChoose(String stuId, String classId,String oldNum) {
        UpdateChoose updateChoose = new UpdateChoose();
        updateChoose.setStuId(stuId.trim());
        updateChoose.setNewClassId(classId.trim());
        updateChoose.setOldClassId(oldNum.trim());
        chooseMapper.updateChoose(updateChoose);
    }


}
