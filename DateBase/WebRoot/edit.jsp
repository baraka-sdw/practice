<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage="" isELIgnored="false"%>
<%@page import="toolBean.DB,valueBean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��ѯ���޸��û�</title>
</head>

<body>
<div align="center"> 
 
 
<form name="modify" method="get" action="ModifyUser"> 
�û����� 
<input type="text" name="username" value="${param.username}" readonly="readonly" /> 
<br /><br /> 
  ��&nbsp;&nbsp;�룺 
  <input type="text" name="password" value="${param.password}" /> 
  <br /><br /> 
  <input type="submit" name="modify" value="�޸��û�" /> 
</form> 
</div>
</body>
</html>
