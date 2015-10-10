<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
  <%@taglib uri="http://www.sdw.com.cn/tag" prefix="sdw"%>
  </head>
  
  <body>
  <sdw:print str="Hello" count="10"/>
  </body>
</html>
