<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="entity.Employee"%>
<%@ page import="dao.EmployeeDAO"%>
<html>
<head>
<style type="text/css">
.s1 {
	cursor: pointer;
}
</style>
<script type="text/javascript">
	function showTime() {
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var day = now.getDate();
		var hour = now.getHours();
		var minutes = now.getMinutes();
		var second = now.getSeconds();
		var times = year + "年" + month + "月" + day + "日" + " " + hour
				+ ((minutes > 10) ? ":" : ":0") + minutes
				+ ((second > 10) ? ":" + second : ":0" + second);
		document.clock.date.value = times;
		setTimeout("showTime()", 1000);
	}
</script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/style1.css">
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
						<a href="#">main</a>
					</h1>
				</div>
				<div id="navigation"></div>
			</div>
			<div id="content">
				<p id="whereami"></p>
				<form action="login.do" method="post">
					<fieldset>
						<legend>登陆</legend>
						用户名:<input type="text" name="username"><%=request.getAttribute("login_name_error") == null
					? ""
					: request.getAttribute("login_name_error").toString()%><br />
						密码&nbsp;&nbsp;&nbsp;:<input type="text" name="password"><%=request.getAttribute("login_pwd_error") == null
					? ""
					: request.getAttribute("login_pwd_error").toString()%><br />
						验证码:<input name="vcode"><%=request.getAttribute("login_code_error") == null
					? ""
					: request.getAttribute("login_code_error").toString()%><br />
						<div>
							<img src="code" onclick="this.src='code?'+Math.random();"
								class="s1" title="更换验证码" style="margin-left: 35px">
						</div>
						<br /> <input type="submit" value="登录" class="button">
						<input type="button" value="注册" class="button"
							onclick="location.href='regist.jsp'">
					</fieldset>
				</form>
			</div>
			<div id="footer">
				<div id="footer_bg"></div>
			</div>
		</div>
	</div>
	<%
		if (request.getAttribute("regist") != null) {
			if (request.getAttribute("regist").toString().equals("1")) {
	%>
	<script type="text/javascript">
		window.alert("注册成功");
	</script>
	<%
		}
		}
	%>
	<% session.setAttribute("login", " "); %>
</body>
</html>
