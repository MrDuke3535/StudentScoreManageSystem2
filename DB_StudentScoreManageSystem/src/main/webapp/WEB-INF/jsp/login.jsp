<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>登录页面</title>
  <link rel="stylesheet" href="../css/bootstrap.css">
    <script src="../js/back.js"></script>
  <style>
    body {
      padding: 30px 0;
    }
    .form-signin {
      max-width: 330px;
      padding: 15px;
      margin: 0 auto;
    }
    .form-signin input {
      margin-top: 6px;
    }
  </style>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    String userName="";
    String password="";
    String rememberPassword="";
    String stuSelect="";
    String teaSelect="";
    String adminSelect="";
    if(cookies!=null)
    for(Cookie cookie:cookies){
        if(cookie.getName().equals("userName")){
            userName=cookie.getValue();
        }else if(cookie.getName().equals("password")){
            password=cookie.getValue();
        }else if(cookie.getName().equals("rememberPassword")){
            if(cookie.getValue().equals("yes")){
                rememberPassword="checked";
            }
        }else if(cookie.getName().equals("user")){
            if(cookie.getValue().equals("student")){
                stuSelect="selected";
                teaSelect="";
                adminSelect="";
            }else if(cookie.getValue().equals("teacher")){
                teaSelect="selected";
                stuSelect="";
                adminSelect="";
            }else if(cookie.getValue().equals("admin")){
                adminSelect="selected";
                stuSelect="";
                teaSelect="";
            }
        }
    }
%>
    <div class="container">
        <form class="form-signin" id="form-test" action="##" method="get" onsubmit="return false">
            <h2 >欢迎登陆</h2>
            <b id="checkAccountAlarm" style="color: #da2723;font-size: 14px"></b>
                <select id="user" name="user" class="form-control">
                <option value="student" <%=stuSelect%>>学生</option>
                <option value="teacher" <%=teaSelect%>>教师</option>
                <option value="admin" <%=adminSelect%>>管理员</option>
            </select>
            <input type="email" name="userName" id="account" class="form-control" placeholder="用户名" required onblur="checkUserName()" value="<%=userName%>"><b id="userNameAlarm" onkeypress="keyEntry()" style="color: #da2723;font-size: 14px"></b>
            <input type="password" name="password" id="password" class="form-control" placeholder="密码" required onblur="checkPassword()" value="<%=password%>" onkeypress="keyEntry()"><b id="passWordAlarm" style="color: #da2723;font-size: 14px"></b>
            <div class="checkbox">
              <label>
                <input type="checkbox" name="rememberPassword" value="yes" <%=rememberPassword %>> Remember me
              </label>
            </div>
            <button id="btn" class="btn btn-lg btn-warning btn-block" type="button" onclick="login()">登陆</button>
          </form>
    </div>
    <!-- 模态框 -->
<%--<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" style="margin-right: 15px;float: right;margin-bottom: 5px;">--%>
    <%--<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>--%>
    <%--显示错误--%>
<%--</button>--%>
    <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" id="myModal">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title">错误提示</h4>
            </div>
            <div class="modal-body">
              <p>用户名或者密码输入错误</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
            </div>
          </div>
        </div>
      </div>
  <script src="../js/jquery.js"></script>
  <script src="../js/bootstrap.js"></script>
  <script src="../js/cookie.js"></script>
</body>
</html>