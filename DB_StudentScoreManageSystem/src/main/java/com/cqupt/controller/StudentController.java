package com.cqupt.controller;

import com.cqupt.pojo.Choose;
import com.cqupt.service.StudentSerice;
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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentSerice studentService = null;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student");
        return mv;
    }

    @RequestMapping("/choose")
    public ModelAndView choose(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        List<Choose> chooseList = studentService.getChooses((String) request.getSession().getAttribute("userName"));
        mv.addObject(chooseList);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/feedback")
    public ModelAndView feedback(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        String stuId = request.getParameter("stuId");
        String classId = request.getParameter("classId");
        studentService.updateFeedback(stuId,classId);
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

}
