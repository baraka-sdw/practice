<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>


</head>

<body >
 <form action="comment" method="post">
  评论：
  <textarea name="comment"></textarea><br/>
  <input type="submit">
 
 </form>
</body>
</html>
