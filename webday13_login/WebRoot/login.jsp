<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<body style="font-size:30pt;">
	登录<br/>
	<span style="color:red;font-size:15pt;">
		<%
			String info = (String)request
			.getAttribute("login_err");
		 %>
		 <%=(info==null ? "" : info) %>
		</span>
	<form action="login.do" method="post">
		用户名:<input type="text" name="username"/><br/>
		密码:<input type="password" name="pwd"/><br/>
		<br/>
		<input type="submit" value="登录"/>
	</form>
</body>