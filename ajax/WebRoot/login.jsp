<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<script type="text/javascript" src="js/my.js"></script>
<script type="text/javascript">
	function check_Inf() {
		var xhr = getXhr();
		document.getElementById("msg").innerHTML = "正在检查,请稍后";
		xhr.open("post", "check.do", true);
		xhr.setRequestHeader('content-type',
				'application/x-www-form-urlencoded');
		var name = document.getElementById("uname").value;
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				if (xhr.responseText.length < 3) {
					document.getElementById("msg").setAttribute("style",
							"color:green");
				} else {
					document.getElementById("msg").setAttribute("style",
							"color:red");
				}
				document.getElementById("msg").innerHTML = xhr.responseText;
			}
		};
		xhr.send("uname=" + name);
	}
</script>
</head>

<body>
	<form action="" method="post">
		<fieldset>
			<legend>注册界面</legend>
			用户名：<input type="text" name="uname" id="uname" /> <span
				style="color:red;" id="msg"></span><br /> <input type="button"
				onclick="check_Inf()" value="检查" /><br /> 密码:<input type="text"
				name="password" /><br /> <input type="submit" value="注册">
		</fieldset>
	</form>
</body>
</html>
