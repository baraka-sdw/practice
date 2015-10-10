<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
</head>

<body>
	<%
		if (request.getAttribute("error") != null) {
			String error = request.getAttribute("error").toString();
	%>
	<h1><%=error%></h1>
	<%
		}
	%>
</body>
</html>
