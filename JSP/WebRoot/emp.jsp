<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
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
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/style1.css">
<meta http-equiv="content-type" content="text/html;charset=gbk">
</head>
<body onload="showTime()">
	<div id="wrap">
		<div id="top content">
			<div id="header">
				<div id="rightheader">
					<form method="get" name="clock">
						<input type="button" name="date" style="margin-top: 27px"><br>
					</form>
				</div>
				<div id="topheader">
					<h1 id="title">
						<a href="#">main</a>
					</h1>
				</div>
				<div id="navigation"></div>
			</div>
			<div id="content">
				<p id="whereami"></p>
				<form action="add.do" method="post">
					<fieldset>
						<legend>添加员工</legend>
						姓名:<input type="text" name="name"><br> 薪水:<input
							type="text" name="salary"><br> 年龄:<input type="text"
							name="age"><br> <input type="submit" value="增加员工">
					</fieldset>
				</form>
			</div>
			<div id="footer">
				<div id="footer_bg"></div>
			</div>
		</div>
	</div>
</body>
</html>
