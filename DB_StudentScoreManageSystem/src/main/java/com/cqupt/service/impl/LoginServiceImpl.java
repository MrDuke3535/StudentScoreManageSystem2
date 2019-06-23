package com.cqupt.service.impl;

import com.cqupt.mapper.AdminMapper;
import com.cqupt.mapper.StudentMapper;
import com.cqupt.mapper.TeacherMapper;
import com.cqupt.pojo.Student;
import com.cqupt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StudentMapper studentMapper = null;
    @Autowired
    private TeacherMapper teacherMapper = null;
    @Autowired
    private AdminMapper adminMapper = null;

    @Override
    public boolean checkPassword(String userName, String password, String user) {
        if(user.equals("student")){
            String realpassword = studentMapper.getPasswordById(userName);
            if(realpassword!=null&&realpassword.equals(password)){
                return true;
            }
        }else if(user.equals("teacher")){
            String realpassword = teacherMapper.getPasswordById(userName);
            if(realpassword!=null&&realpassword.equals(password)){
                return true;
            }
        }else if(user.equals("admin")){
            String realpassword = adminMapper.getPasswordById(userName);
            if(realpassword!=null&&realpassword.equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void setRemember(HttpServletRequest request,HttpServletResponse response, String userName, String password, String user, String rememberPassword) {
        if(rememberPassword!=null&&rememberPassword.equals("yes")){//如果记住密码就用cookie记住密码
            Cookie userNameCookie = new Cookie("userName",userName);
            Cookie passwordCookie = new Cookie("password",password);
            Cookie rememberCookie = new Cookie("rememberPassword",rememberPassword);
            Cookie userCookie = new Cookie("user",user);
            userNameCookie.setMaxAge(864000);//设置浏览器记住密码的最长时间为10天
            passwordCookie.setMaxAge(864000);
            rememberCookie.setMaxAge(864000);
            userCookie.setMaxAge(864000);
            response.addCookie(userNameCookie);
            response.addCookie(passwordCookie);
            response.addCookie(rememberCookie);
            response.addCookie(userCookie);
        }else{//若用户未选择记住密码
            Cookie[] cookies = request.getCookies();
            if(cookies!=null&&cookies.length>0){
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("userName")||cookie.getName().equals("password")
                            ||cookie.getName().equals("rememberPassword")||cookie.getName().equals("user")){
                        cookie.setMaxAge(0);//取消当前cookie
                        response.addCookie(cookie);
                    }
                }
            }
        }
    }
}
