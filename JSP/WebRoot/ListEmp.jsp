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
       var times=year+"��"+month+"��"+day+"��"+" "+hour+((minutes>10)?":":":0")+minutes+((second>10)? ":"+second:":0"+second);
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
						<a href="logout.do">�ǳ�</a>
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
						<legend>Ա����Ϣ�б�</legend>
						<table class="table">
							<tr class="table header">
								<td>���</td>
								<td>����</td>
								<td>нˮ</td>
								<td>����</td>
								<td>����</td>
							</tr>
							<c:forEach var="emp" items="${emp}">
							<tr class="row${emp.id%2+1}">
								<td>${emp.id}</td>
								<td>${emp.name }</td>
								<td>${emp.salary }</td>
								<td>${emp.age }</td>
								
								<td><a href="delete.do?id=${emp.id }>"
									onclick="return confirm ȷ��ɾ��${emp.name}?>"
									style="color: red">ɾ��</a> <a
									href="ModifyEmp.jsp?id=${emp.id }&name=${emp.name }&salary=${emp.salary })&age=${emp.age}">�޸�</a></td>
							</tr>
							</c:forEach>
						</table>
						
						<input type="button" class="button" value="���Ա��"
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
	 window.alert("��¼�ɹ�");
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
	 window.alert("�޸ĳɹ�");
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
	 window.alert("ɾ���ɹ�");
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
	 window.alert("��ӳɹ�");
	 </script>
	<%
	     }
		}
	%>
	<h1><%=path%></h1>
	<h1><%=basePath%></h1>
</body>
</html>
