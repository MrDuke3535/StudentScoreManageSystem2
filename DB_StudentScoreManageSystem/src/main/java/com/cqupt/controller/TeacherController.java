package com.cqupt.controller;

import com.cqupt.pojo.Classes;
import com.cqupt.pojo.SeeStudent;
import com.cqupt.service.TeacherService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService = null;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("teacher");
        return mv;
    }

    @RequestMapping("/classes")
    public ModelAndView classes(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        List<Classes> classes = teacherService.getClasses((String) request.getSession().getAttribute("userName"));
        mv.addObject(classes);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/seeStudent")
    public ModelAndView addScore(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("seeStudent");
        return mv;
    }

    @RequestMapping("/score")
    public ModelAndView score(HttpServletRequest request,HttpServletResponse response){
        String classId = request.getParameter("classId");
        ModelAndView mv = new ModelAndView();
        List<SeeStudent> seeStudents = teacherService.getSeeStudent(classId);
        mv.addObject(seeStudents);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/update")
    public ModelAndView update(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        String classId = request.getParameter("classId");
        String score = request.getParameter("score");
        String stuId = request.getParameter("stuId");
        teacherService.updateScore(stuId,classId,score);
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/rate")
    public ModelAndView rate(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        String classId = request.getParameter("classId");
        Map<String,Integer> rate = teacherService.getScoreRate(classId);
        mv.addObject(rate);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/search")
    public ModelAndView search(HttpServletRequest request,HttpServletResponse response){
        String classId = request.getParameter("classId");
        String keyWord = request.getParameter("keyWord");
        List<SeeStudent> seeStudents ;
        if(keyWord==null||keyWord.trim().equals("")){
            seeStudents = teacherService.getSeeStudent(classId);
        }else {
            seeStudents = teacherService.getStudentByKeyWord(classId,keyWord);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject(seeStudents);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String classId = request.getParameter("classId");
        String filePath = teacherService.downloadTable(classId);
        HttpHeaders headers = new HttpHeaders();
        File file = new File(filePath);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", classId+".xls");

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
             headers, HttpStatus.CREATED);

    }


}
