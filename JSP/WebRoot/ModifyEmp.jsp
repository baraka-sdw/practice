<%@ page language="java" import="java.util.*" pageEncoding="gbk"
	contentType="text/html; charset=gbk"%>
<%@ page import="entity.Employee"%>
<%@ page import="dao.EmployeeDAO"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<script type="text/javascript">
	function showTime() {
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var day = now.getDate();
		var hour = now.getHours();
		var minutes = now.getMinutes();
		var second = now.getSeconds();
		var times = year + "��" + month + "��" + day + "��" + " " + hour
				+ ((minutes > 10) ? ":" : ":0") + minutes
				+ ((second > 10) ? ":" + second : ":0" + second);
		document.clock.date.value = times;
		setTimeout("showTime()", 1000);

	}
</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<input type="button" class="button"name="date" style="margin-top: 27px"><br>
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
				<%
					EmployeeDAO dao = new EmployeeDAO();
					List<Employee> emps = dao.findAll();
					request.setAttribute("e", emps);
				%>
              <c:forEach var="e" items="${e}">
              <c:if test="${e.id==${param.id }">
				<!-- ����Ϊ��һ��ҳ�洫ֵ -->
				<form action="modify.do" method="post">
					<fieldset>
						<legend>�������޸ĵ���Ϣ</legend>
						<br> id :<input type="text" name="id" value="${param.id}">
						<br>������<input type="text" name="name"
							value="${e.name}"><br>
						<!--��Ϊ�ڶ���ҳ�洫ֵ<%=session.getAttribute("name"
							+ Integer.parseInt(request.getParameter("id")))%> -->
						</c:if>
						</c:forEach>
						нˮ��<input type="text" name="salary" value="${param.salary }"><br>
						���䣺<input type="text" name="age" value="${param.age }"><br>
						<input type="submit" value="�ύ�޸�"
							onclick=" return confirm('ȷ���޸ģ�')"class="button"> <a></a>
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

