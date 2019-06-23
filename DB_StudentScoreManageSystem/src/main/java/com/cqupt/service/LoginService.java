package com.cqupt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    public boolean checkPassword(String userName,String password,String user);
    public void setRemember(HttpServletRequest request, HttpServletResponse response, String userName, String password, String user, String rememberPassword);
}
