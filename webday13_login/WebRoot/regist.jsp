<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<body style="font-size:30pt;">
	注册<br/>
	<form action="regist.do" method="post">
		用户名:<input type="text" name="username"/><br/>
		密码:<input type="password" name="pwd"/><br/>
		性别:男<input type="radio" name="sex" value="m"/>
		女<input type="radio" name="sex" value="f"/><br/>
		验证码:<img src="checkCode" id="img1"/>
		<a href="javascript:;" onclick="document.getElementById('img1').src='checkCode?'+(new Date()).getTime()">看不清，换一个</a>
		<input type="text" name="number"/>
		<span style="color:red;font-size:15pt;">
		<%
			String info = (String)request
			.getAttribute("regist_err");
		 %>
		 <%=(info==null ? "" : info) %>
		</span>
		<br/>
		<input type="submit" value="注册"/>
	</form>
</body>