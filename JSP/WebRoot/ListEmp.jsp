<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page import="entity.Employee"%>
<%@ page import="dao.EmployeeDAO"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
   Object uname=session.getAttribute("uname");
   if(uname==null){
     response.sendRedirect("login.jsp");
   }
 %>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/style1.css">
<style type="text/css">
td {
	border: 2px solid black;
	text-align: center;
}

table {
	border-collapse: collapse;
	margin-left: 30px;
}

legend {
	font-size: 40px;
	text-align: center;
}

input {
	margin-left: 100px;
}
</style>
<script type="text/javascript">
  function showTime() {
       var now =new Date();
       var year=now.getFullYear();
       var month=now.getMonth()+1;
       var day=now.getDate();
       var hour=now.getHours();
       var minutes=now.getMinutes();
       var second=now.getSeconds();
       var times=year+"年"+month+"月"+day+"日"+" "+hour+((minutes>10)?":":":0")+minutes+((second>10)? ":"+second:":0"+second);
       document.clock.date.value=times;
       setTimeout("showTime()", 1000);
  }
  </script>
</head>
<body onload="showTime()">
	<div id="wrap">
			<div id="top content">
			<div id="header">
				<div id="rightheader">
					<form method="get" name="clock">
						<input type="button" class="button" name="date"
							style="margin-top: 27px"><br>
					</form>
				</div>
				<div id="topheader">
					<h1 id="title">
						<a href="logout.do">登出</a>
					</fieldset>
					</h1>
				</div>
				<div id="navigation"></div>
			</div>
			<div id="content">
				<p id="whereami"></p>
				<%
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
									EmployeeDAO dao = new EmployeeDAO();
									List<Employee> emps = dao.findAll();
									request.setAttribute("emp", emps);
				%>

				<form>
					<fieldset style="width: 300px; ">
						<legend>员工信息列表</legend>
						<table class="table">
							<tr class="table header">
								<td>编号</td>
								<td>姓名</td>
								<td>薪水</td>
								<td>年龄</td>
								<td>操作</td>
							</tr>
							<c:forEach var="emp" items="${emp}">
							<tr class="row${emp.id%2+1}">
								<td>${emp.id}</td>
								<td>${emp.name }</td>
								<td>${emp.salary }</td>
								<td>${emp.age }</td>
								
								<td><a href="delete.do?id=${emp.id }>"
									onclick="return confirm 确认删除${emp.name}?>"
									style="color: red">删除</a> <a
									href="ModifyEmp.jsp?id=${emp.id }&name=${emp.name }&salary=${emp.salary })&age=${emp.age}">修改</a></td>
							</tr>
							</c:forEach>
						</table>
						
						<input type="button" class="button" value="添加员工"
							onclick="location.href='emp.jsp'">
				</form>
			</div>
			<div id="footer">
				<div id="footer_bg"></div>
			</div>
		</div>
	</div>
	<%
	
		if(request.getAttribute("msg")!=null){
			   if(request.getAttribute("msg").toString().equals("1")){
	%>
	<script type="text/javascript">
	 window.alert("登录成功");
	 </script>
	<%
	     }
		}
	%>
	<%
	
		if(request.getAttribute("modify")!=null){
			   if(request.getAttribute("modify").toString().equals("1")){
	%>
	<script type="text/javascript">
	 window.alert("修改成功");
	 </script>
	<%
	     }
		}
	%>
	<%
	
		if(request.getAttribute("delete")!=null){
			   if(request.getAttribute("delete").toString().equals("1")){
	%>
	<script type="text/javascript">
	 window.alert("删除成功");
	 </script>
	<%
	     }
		}
	%>
	<%
	
		if(request.getAttribute("add")!=null){
			   if(request.getAttribute("add").toString().equals("1")){
	%>
	<script type="text/javascript">
	 window.alert("添加成功");
	 </script>
	<%
	     }
		}
	%>
	<h1><%=path%></h1>
	<h1><%=basePath%></h1>
</body>
</html>
