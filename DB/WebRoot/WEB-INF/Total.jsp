<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css" />
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>����û�</title>
</head>

<body>
<form method="post" action="AddUser">
<div align="center"><blockquote>
	<div>
		<font face="����" size="6" color="#80ffff">
			<strong>
				�û�����
				<input name="username" type="text" />
				<br />
				<br />
				��&nbsp;�룺 <input name="password" type="text" />
				<br />
				
				<input type="submit" value="���" />
				
				<input type="reset" value="ȡ��" /><br /><br />
				<br />
	</div>
	<form name="modify" id="form-1" action="ModifyUser"></form>  

</form>
</body>
</html>