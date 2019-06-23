package com.cqupt.controller;

import com.cqupt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService = null;

    /**
     * 网站首页
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/checkAccount")
    public ModelAndView checkAccount(HttpServletRequest request , HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        String user = request.getParameter("user");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberPassword = request.getParameter("rememberPassword");
        boolean right = loginService.checkPassword(userName,password,user);
        if(right){
            mv.addObject("success");
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("userName",userName);
            loginService.setRemember(request,response,userName,password,user,rememberPassword);
        }else {
            mv.addObject("fail");
        }
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("userName");
        ModelAndView mv = new ModelAndView();
        mv.addObject("success");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

}
