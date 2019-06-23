<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>成绩查询</title>
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

<body onload="loadCourse()">

	<div class="container-fluid">
		<h3 class="wq-title">
			<span class="logo">
				<img src="../images/cy.jpg" alt="" style="height: 80px">
			</span>
			成绩查询
            <span style="float: right;margin-top: 30px;margin-right: 10px">
                学生:<%=request.getSession().getAttribute("userName")%>,
            <a href="javascript:logout()">退出</a>
            </span>
		</h3>
        <div class="row">
            <table class="table table-bordered table-hover wq-main-table ">
                <!--错题项目-->
                <thead>
                <tr>
                    <th class="col-md-1 text-center" style="width: 5%">编号</th>
                    <th class="col-md-3 text-center" style="width: 17%">
                        <div style="margin-left: 44%">
                            <span style="float:left;margin-top: 15px">学号</span>
                        </div>
                    </th>
                    <th class="col-md-3 text-center" style="width: 17%">
                        <div style="margin-left: 44%">
                            <span style="float:left;margin-top: 15px">姓名</span>
                        </div>
                    </th>
                    <th class="col-md-3 text-center" style="width: 17%">
                        <div style="margin-left: 44%">
                            <span style="float:left;margin-top: 15px">班级号</span>
                        </div>
                    </th>
                    <th class="col-md-3 text-center" style="width: 17%">
                        <div style="margin-left: 44%">
                            <span style="float:left;margin-top: 15px">课程</span>
                        </div>
                    </th>
                    <th class="col-md-3 text-center" style="width: 17%;text-align: center">
                        <div style="margin-left: 44%">
                            <span style="float:left;margin-top: 15px">教师</span>
                        </div>
                    </th>
                    <th class="col-md-3 text-center" style="width: 17%">
                        <div style="margin-left: 44%">
                            <span style="float:left;margin-top: 15px">成绩</span>
                        </div>
                    </th>
                    <th class="col-md-2 text-center" style="width: 10%">操作</th>
                </tr>
                </thead>

                <tbody id="tbody">

                <%--<tr class="text-center middle">--%>
                <%--<td>1</td>--%>
                <%--<td>2017214712</td>--%>
                <%--<td>王京</td>--%>
                <%--<td>数据库原理</td>--%>
                <%--<td>胡军</td>--%>
                <%--<td>99</td>--%>
                <%--<td>--%>
                <%--<button class="btn btn-danger">反馈</button>--%>
                <%--</td>--%>
                <%--</tr>--%>
                <%--<input type="button" value="退出" onclick="logout()">--%>

                </tbody>
            </table>
        </div>
        <div class="modal fade" id="feedback">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <h4>添加反馈信息</h4>
                </div>

            </div>
        </div>


	</div>

</body>
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/manager.js"></script>


</html>