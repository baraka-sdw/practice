<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>添加用户</title>
</head>

<body>
<form method="post" action="AddUser">
<div align="center"><blockquote>
	<div>
		<font face="宋体" size="6" color="#80ffff">
			<strong>
				用户名：
				<input name="username" type="text" />
				<br />
				<br />
				密&nbsp;码： <input name="password" type="text" />
				<br />
				
				<input type="submit" value="添加" />
				
				<input type="reset" value="取消" /><br /><br />
				<br />
	</div>
	<form name="modify" id="form-1" action="ModifyUser"></form>  

</form>
</body>
</html>