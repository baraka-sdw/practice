
<%@page import="java.nio.charset.Charset"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/style1.css">
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
				<h1>注册</h1>
				<form action="regist.do" method="post">	
					用户名&nbsp;&nbsp;&nbsp;:<input type="text" class="inputgri" name="username" />
					<%=request.getAttribute("regist_error") == null ? "": request.getAttribute("regist_error")%>
					<%=request.getAttribute("namenull") == null ? "": request.getAttribute("namenull")%><br/> 
					真实姓名:<input type="text" class="inputgri" name="name" /> <br/>
					密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:<input type="password" class="inputgri" name="pwd" /> <br/>
					性别&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: 男 <input type="radio" class="inputgri" name="sex" value="男" checked="checked"/> 
					女 <input type="radio" class="inputgri" name="sex" value="女" /><br/> 
						验证码&nbsp;&nbsp;&nbsp;:<input type="text"class="inputgri" name="number" /><br/>
						 <img src="code" onclick="this.src='code?'+Math.random();" class="s1" title="更换验证码"
						style="margin-left: 35px"><br/>
						<input type="submit" class="button" value="提交" />
					
				</form>
			</div>
			<div id="footer">
				<div id="footer_bg"></div>
			</div>
		</div>
	</div>
</body>
<p><%=Charset.defaultCharset()%>
<p>
</html>
