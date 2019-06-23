package com.cqupt.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String user = (String) request.getSession().getAttribute("user");
        if(user==null||user.equals("")){
            response.sendRedirect("../login/index.do");
        }else if(user.equals("student")){
            return true;
        }else if(user.equals("teacher")){
            response.sendRedirect("../teacher/index.do");
        }else if(user.equals("admin")){
            response.sendRedirect("../admin/index.do");
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
