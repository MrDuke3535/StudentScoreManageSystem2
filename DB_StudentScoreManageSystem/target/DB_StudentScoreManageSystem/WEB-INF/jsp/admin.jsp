<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>成绩管理系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../css/bootstrap.css">
    <script src="../js/jquery.js" charset="utf-8"></script>
    <script src="../js/highcharts.js" charset="utf-8"></script>
    <script src="../js/exporting.js" charset="utf-8"></script>
    <script src="../js/export-data.js" charset="utf-8"></script>
	<script src="../js/back.js" charset="utf-8"></script>
	<style>
		.wq-title {
			padding-bottom: 40px;
		}
		.wq-title:hover {
			transition: all .5s;
			text-shadow: 0 0 5px #999;
		}
		.wq-main-table thead {
			background: rgba(0, 0, 0, .3);
			color: #333;
		}
		.wq-main-table td {
			max-height: 50px;
			word-break: break-all;
		}
		.center-vertical {
			position: relative;
			top: 50%;
			transform: translateY(-50%);
		}
		.wq-add .modal-content {
			padding: 20px;
		}
	</style>
</head>

<body onload="admin_init()">

	<div class="container-fluid">
		<h3 class="wq-title">
			<span class="logo">
				<img src="../images/cy.jpg" alt="" style="height: 80px">
			</span>
			成绩管理系统
            <span style="float: right;margin-top: 30px;margin-right: 10px">
                管理员:<%=request.getSession().getAttribute("user")%>,
            <a href="javascript:logout()">退出</a>
            </span>
		</h3>
        <div class="container" style="width: 100%">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="tabbable" id="tabs-315319">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="#panel-35842" data-toggle="tab" style="font-size: 18px">学生</a>
                            </li>
                            <li >
                                <a href="#panel-698000" data-toggle="tab" style="font-size: 18px">教师</a>
                            </li>
                            <li >
                                <a href="#panel-698003" data-toggle="tab" style="font-size: 18px">课程</a>
                            </li>
                            <li >
                                <a href="#panel-698001" data-toggle="tab" style="font-size: 18px">班级</a>
                            </li>
                            <li >
                                <a href="#panel-698002" data-toggle="tab" style="font-size: 18px">选课</a>
                            </li>
                        </ul>
                        <div class="tab-content">

                            <div class="tab-pane active" id="panel-35842">
                                <div style="margin-top: 30px">
                                <div class="form-inline" style="margin-left: 5%;margin-bottom: 25px;margin-top: 10px;float: left">
                                    <label for="search" class="sr-only">请输入学号或学生姓名</label>
                                    <input type="text" class="form-control input-lg" onkeypress="keyEntry3()" id="search3" placeholder="请输入学号或学生名">

                                    <button  class="btn  btn-default btn-lg" onclick="search3()" id="searchBtn3">查询</button>
                                </div>

                                <button type="button" class="btn btn-lg" data-toggle="modal" data-target="#studentAdd" style="margin-right: 15px;float: right;margin-bottom: 5px;">
                                    <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                                    添加学生
                                </button>
                                <!-- 添加学生成绩部分 -->
                                    <div class="modal fade wq-add" id="studentAdd">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <!--细节划分-->
                                                <h4>添加学生信息:</h4>
                                                <div class="form-group">
                                                    <label for="wq-id">学生学号</label>
                                                    <input type="text" class="form-control" id="wq-id" placeholder="学生学号" value="">
                                                </div>
                                                <div class="form-group">
                                                    <label for="wq-stuName">学生姓名</label>
                                                    <input type="text" class="form-control" id="wq-stuName" placeholder="学生姓名" value="">
                                                </div>
                                                <div class="form-group">
                                                    <label for="wq-password">学生密码</label>
                                                    <input type="text" class="form-control" id="wq-password" placeholder="学生密码" value="">
                                                </div>
                                                <div class="text-center">
                                                    <button type="button" onclick="studentAdd()" class="btn btn-success" data-dismiss="modal" id="studentSubmit">提交</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="container" style="width: 100%;">
                                        <div class="row clearfix">
                                            <div class="col-md-12 column">
                                                <table class="table table-striped table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>
                                                            编号
                                                        </th>
                                                        <th>
                                                            学号
                                                        </th>
                                                        <th>
                                                            姓名
                                                        </th>
                                                        <th>
                                                            密码
                                                        </th>
                                                        <th>
                                                            操作
                                                        </th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="tbody">

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="panel-698000">
                                <div style="margin-top: 30px">
                                    <div class="form-inline" style="margin-left: 5%;margin-bottom: 25px;margin-top: 10px;float: left">
                                        <label for="search" class="sr-only">请输入教师工号或教师姓名</label>
                                        <input type="text" class="form-control input-lg" onkeypress="keyEntry5()" id="search5" placeholder="请输入教师工号或教师姓名">

                                        <button  class="btn  btn-default btn-lg" onclick="searchTeacher()" id="searchBtn5">查询</button>
                                    </div>

                                    <button type="button" class="btn btn-lg" data-toggle="modal" data-target="#teacherAdd" style="margin-right: 15px;float: right;margin-bottom: 5px;">
                                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                                        添加教师
                                    </button>
                                    <!-- 添加学生成绩部分 -->
                                    <div class="modal fade wq-add" id="teacherAdd">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <!--细节划分-->
                                                <h4>添加教师信息:</h4>
                                                <div class="form-group">
                                                    <label for="wq-id">教师工号</label>
                                                    <input type="text" class="form-control" id="tea_id" placeholder="教师工号" value="">
                                                </div>
                                                <div class="form-group">
                                                    <label for="wq-stuName">教师姓名</label>
                                                    <input type="text" class="form-control" id="tea_Name" placeholder="教师姓名" value="">
                                                </div>
                                                <div class="form-group">
                                                    <label for="wq-password">教师密码</label>
                                                    <input type="text" class="form-control" id="tea_password" placeholder="教师密码" value="">
                                                </div>
                                                <div class="text-center">
                                                    <button type="button" onclick="teacherAdd()" class="btn btn-success" data-dismiss="modal" id="teacherSubmit">提交</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="container" style="width: 100%;">
                                        <div class="row clearfix">
                                            <div class="col-md-12 column">
                                                <table class="table table-striped table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>
                                                            编号
                                                        </th>
                                                        <th>
                                                            工号
                                                        </th>
                                                        <th>
                                                            姓名
                                                        </th>
                                                        <th>
                                                            密码
                                                        </th>
                                                        <th>
                                                            操作
                                                        </th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="tbody2">

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="panel-698003">
                                <div style="margin-top: 30px">
                                    <div class="form-inline" style="margin-left: 5%;margin-bottom: 25px;margin-top: 10px;float: left">
                                        <label for="search" class="sr-only">请输入课程号或课程名</label>
                                        <input type="text" class="form-control input-lg" onkeypress="keyEntry6()" id="searchCourse" placeholder="请输入课程号或课程名">
                                        <button  class="btn  btn-default btn-lg" onclick="searchCourse()" id="searchCourseBtn">查询</button>
                                    </div>
                                    <button type="button" class="btn btn-lg" data-toggle="modal" data-target="#courseAdd" style="margin-right: 15px;float: right;margin-bottom: 5px;">
                                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                                        添加课程
                                    </button>
                                    <div class="modal fade wq-add" id="courseAdd">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <!--细节划分-->
                                                <h4>添加课程信息:</h4>
                                                <div class="form-group">
                                                    <label for="courseId">课程号</label>
                                                    <input type="text" class="form-control" id="courseId" placeholder="课程号" value="">
                                                </div>
                                                <div class="form-group">
                                                    <label for="courseName">课程名</label>
                                                    <input type="text" class="form-control" id="courseName" placeholder="课程名" value="">
                                                </div>
                                                <div class="text-center">
                                                    <button type="button" onclick="addCourse()" class="btn btn-success" data-dismiss="modal" id="courseSubmit">提交</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="container" style="width: 100%;">
                                        <div class="row clearfix">
                                            <div class="col-md-12 column">
                                                <table class="table table-striped table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>
                                                            编号
                                                        </th>
                                                        <th>
                                                            课程号
                                                        </th>
                                                        <th>
                                                            课程名
                                                        </th>
                                                        <th>
                                                            操作
                                                        </th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="tbody5">

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="tab-pane" id="panel-698001">
                                <div style="margin-top: 30px">
                                    <div class="form-inline" style="margin-left: 5%;margin-bottom: 25px;margin-top: 10px;float: left">
                                        <label for="search" class="sr-only">班级号、课程号或课程名</label>
                                        <input type="text" class="form-control input-lg" onkeypress="keyEntry7()" id="searchClass" placeholder="班级号、课程号或课程名">
                                        <button  class="btn  btn-default btn-lg" onclick="search6()" id="searchClassBtn">查询</button>
                                    </div>

                                    <button type="button" class="btn btn-lg" data-toggle="modal" data-target="#classAdd" style="margin-right: 15px;float: right;margin-bottom: 5px;">
                                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                                        添加课程
                                    </button>
                                    <!-- 添加学生成绩部分 -->
                                    <div class="modal fade wq-add" id="classAdd">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <!--细节划分-->
                                                <h4>添加课程信息:</h4>
                                                <div class="form-group">
                                                    <label for="wq-id">教学班号</label>
                                                    <input type="text" class="form-control" id="class_id" placeholder="教学班号" value="">
                                                </div>
                                                <div class="form-group">
                                                    <label for="wq-stuName">教师工号/教师姓名</label>
                                                    <input type="text" class="form-control" id="teacher" placeholder="教师工号/教师姓名" value="">
                                                </div>
                                                <div class="form-group">
                                                    <label for="wq-password">课程号/课程名</label>
                                                    <input type="text" class="form-control" id="course" placeholder="课程号" value="">
                                                </div>
                                                <div class="text-center">
                                                    <button type="button" onclick="classesAdd()" class="btn btn-success" data-dismiss="modal" id="classSubmit">提交</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="container" style="width: 100%;">
                                        <div class="row clearfix">
                                            <div class="col-md-12 column">
                                                <table class="table table-striped table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>
                                                            编号
                                                        </th>
                                                        <th>
                                                            课程号
                                                        </th>
                                                        <th>
                                                            课程名
                                                        </th>
                                                        <th>
                                                            班级号
                                                        </th>
                                                        <th>
                                                            教师工号
                                                        </th>
                                                        <th>
                                                            教师名
                                                        </th>
                                                        <th>
                                                            操作
                                                        </th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="tbody3">

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="panel-698002">
                                <p>
                                <div style="margin-top: 30px">
                                    <div class="form-inline" style="margin-left: 5%;margin-bottom: 25px;margin-top: 10px;float: left">
                                        <label for="search" class="sr-only">请输入学生学号或姓名</label>
                                        <input type="text" class="form-control input-lg" onkeypress="keyEntry8()" id="search" placeholder="请输入学生学号或姓名">

                                        <button  class="btn  btn-default btn-lg" onclick="searchChoose()" id="searchBtn">查询</button>
                                    </div>

                                    <button type="button" class="btn btn-lg" data-toggle="modal" data-target="#chooseAdd" style="margin-right: 15px;float: right;margin-bottom: 5px;">
                                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                                        添加选课
                                    </button>
                                    <!-- 添加学生成绩部分 -->
                                <div class="modal fade wq-add" id="chooseAdd">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <!--细节划分-->
                                            <h4>添加选课信息:</h4>
                                            <div class="form-group">
                                                <label for="student-id">学号/姓名</label>
                                                <input type="text" class="form-control" id="student-id" placeholder="学号/姓名" value="">
                                            </div>
                                            <div class="form-group">
                                                <label for="class-id">教学班号</label>
                                                <input type="text" class="form-control" id="class-id" placeholder="教学班号" value="">
                                            </div>
                                            <div class="text-center">
                                                <button type="button" onclick="chooseAdd()" class="btn btn-success" data-dismiss="modal" id="chooseSubmit">提交</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                    <div class="container" style="width: 100%;">
                                        <div class="row clearfix">
                                            <div class="col-md-12 column">
                                                <table class="table table-striped table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>
                                                            编号
                                                        </th>
                                                        <th>
                                                            学号
                                                        </th>
                                                        <th>
                                                            姓名
                                                        </th>
                                                        <th>
                                                            班级号
                                                        </th>
                                                        <th>
                                                            科目
                                                        </th>
                                                        <th>
                                                            教师
                                                        </th>
                                                        <th>
                                                            成绩
                                                        </th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="tbody4">

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


	</div>

</body>
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/manager.js"></script>
<script>
	
	// 获取数据（ajax）
	// 遍历表格
	// 遍历 （for）
	// 创建DOM（通过创建DOM元素或者直接字符串拼接）
	// 放到该放入的dom元素里
</script>

</html>