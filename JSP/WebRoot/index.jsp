<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	
  </head>
  <%
   %>
  <body>
<%
    List<String> a=new ArrayList<String>();
    a.add("贝贝");
    a.add("晶晶");
    a.add("欢欢");
    a.add("莹莹");
    a.add("贝贝");
    request.setAttribute("a", a);
 %>
 <b><c:out value="不指定begin和end的迭代"></c:out></b><br/>
 <c:forEach var="fuwa" items="${a}"><c:out value="${fuwa}"></c:out><br/></c:forEach>
 <b><c:out value="指定begin和end的迭代"></c:out></b><br/>
 <c:forEach var="fuwa" items="${a}" begin="1" end="3" step="2"><c:out value="${fuwa}" ></c:out><br/></c:forEach>
 <b><c:out value="输出整个迭代的信息"></c:out></b><br/>
 <c:forEach var="fuwa" items="${a}" begin="3" end="4" varStatus="s" step="1">
 所在位置，即索引:<c:out value="${s.index }"></c:out><br/>
 总共迭代的次数:<c:out value="${s.count }"></c:out><br/>
是否为第一个位置:<c:out value="${s.first }"></c:out><br/>
是否为最后一个位置:<c:out value="${s.last}"></c:out>
 </c:forEach>
 
  </body>
</html>
