package com.cqupt.controller;

import com.cqupt.pojo.AdminStudent;
import com.cqupt.pojo.Course;
import com.cqupt.pojo.Student;
import com.cqupt.pojo.Teacher;
import com.cqupt.service.AdminSevice;
import com.cqupt.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminSevice adminSevice = null;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin");
        return mv;
    }

    @RequestMapping("/getStudents")
    public ModelAndView getStudent(){
        ModelAndView mv = new ModelAndView();
        List<Student> students = adminSevice.getStudents();
        mv.addObject(students);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/getTeachers")
    public ModelAndView getTeachers(){
        ModelAndView mv = new ModelAndView();
        List<Teacher> teachers = adminSevice.getTeachers();
        mv.addObject(teachers);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/getClasses")
    public ModelAndView getClasses(){
        ModelAndView mv = new ModelAndView();
        List<Course> courses = adminSevice.getCourses();
        mv.addObject(courses);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/getChooses")
    public ModelAndView getChooses(){
        ModelAndView mv = new ModelAndView();
        List<AdminStudent> adminStudents = adminSevice.getAdminStudents();
        mv.addObject(adminStudents);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
        String stuId = request.getParameter("stuId");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        adminSevice.updateStudent(stuId,name,password);
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/delete")
    public ModelAndView delete(HttpServletRequest request,HttpServletResponse response){
        String stuId = request.getParameter("stuId");
        System.out.println(stuId);
        adminSevice.deleteStudent(stuId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/addStudent")
    public ModelAndView addStudent(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        String stuId = request.getParameter("stuId");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        adminSevice.addStudent(stuId,name,password);
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/search")
    public ModelAndView search(HttpServletRequest request,HttpServletResponse response){
        String keyWord = request.getParameter("keyWord");
        List<Student> studentList = adminSevice.getStudentsByKeyWord(keyWord);
        ModelAndView mv = new ModelAndView();
        mv.addObject(studentList);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/addTeacher")
    public ModelAndView addTeacher(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        adminSevice.addTeacher(id,name,password);
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/updateTeacher")
    public ModelAndView updateTeacher(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        adminSevice.updateTeacher(id,name,password);
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        mv.addObject("success");
        return mv;
    }

    @RequestMapping("/deleteTeacher")
    public ModelAndView deleteTeacher(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        adminSevice.deleteTeacher(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/searchTeacher")
    public ModelAndView searchTeacher(HttpServletRequest request,HttpServletResponse response){
        String keyWord = request.getParameter("keyWord");
        List<Teacher> teachers = adminSevice.searchTeacherByKeyWord(keyWord);
        ModelAndView mv = new ModelAndView();
        mv.addObject(teachers);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/addCourse")
    public ModelAndView addCourse(HttpServletRequest request,HttpServletResponse response){
        String couserId = request.getParameter("courseId");
        String courseName = request.getParameter("courseName");
        adminSevice.addCourse(couserId,courseName);
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/deleteCourse")
    public ModelAndView deleteCourse(HttpServletRequest request){
        String courseId = request.getParameter("id");
        adminSevice.deleteCourse(courseId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/updateCourse")
    public ModelAndView updateCourse(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        adminSevice.updateCourse(id,name);
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/searchCourse")
    public ModelAndView searchCourse(HttpServletRequest request,HttpServletResponse response){
        String keyWord = request.getParameter("keyWord");
        List<Course> courses = adminSevice.getCourseByKeyWord(keyWord);
        ModelAndView mv = new ModelAndView();
        mv.addObject(courses);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/addClasses")
    public ModelAndView addClasses(HttpServletRequest request,HttpServletResponse response){
        String classId = request.getParameter("classId");
        String teacher = request.getParameter("teacher");
        String course = request.getParameter("course");
        adminSevice.addClasses(classId,teacher,course);
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/deleteClasses")
    public ModelAndView deleteClasses(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        adminSevice.deleteClassById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/updateClass")
    public ModelAndView updateClass(HttpServletRequest request,HttpServletResponse response){
        String teacheriId = request.getParameter("teacherId");
        String classId = request.getParameter("classId");
        adminSevice.updateClasses(classId,teacheriId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/serachClasses")
    public ModelAndView serachClasses(HttpServletRequest request,HttpServletResponse response){
        String keyWord = request.getParameter("keyWord");
        List<Course> courses = adminSevice.getClassesByKeyWord(keyWord);
        ModelAndView mv = new ModelAndView();
        mv.addObject(courses);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/addChoose")
    public ModelAndView addChoose(HttpServletRequest request,HttpServletResponse response){
        String student = request.getParameter("student");
        String classes = request.getParameter("classes");
        adminSevice.addChoose(student,classes);
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/searchChoose")
    public ModelAndView searchChoose(HttpServletRequest request,HttpServletResponse response){
        String keyWord = request.getParameter("keyWord");
        List<AdminStudent> adminStudents = adminSevice.getChooseByKeyWord(keyWord);
        ModelAndView mv = new ModelAndView();
        mv.addObject(adminStudents);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/deleteChoose")
    public ModelAndView deleteChoose(HttpServletRequest request,HttpServletResponse response){
        String stuId = request.getParameter("stuId");
        String classId = request.getParameter("classId");
        adminSevice.deleteChoose(stuId,classId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/updateChoose")
    public ModelAndView updateChoose(HttpServletRequest request,HttpServletResponse response){
        String stuId = request.getParameter("stuId");
        String classId = request.getParameter("classId");
        String oldNum = request.getParameter("oldNum");
        System.out.println(oldNum);
        adminSevice.updateChoose(stuId,classId,oldNum);
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }
}
