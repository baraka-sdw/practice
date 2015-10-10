<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>

</head>

<body >
   <%=application.getAttribute("count").toString()%>
 <a style="color:red"></a>
 <a href="logout">logout</a>
</body>
</html>
