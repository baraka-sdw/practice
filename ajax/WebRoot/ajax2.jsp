<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<html>
<head>
<script type="text/javascript">
	function getText() {
		var xhr = getXhr();
		var uri="post";
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				alert(xhr.responseText);
			}
		};
		xhr.open("post",encodeURI(URI), true);
		xhr.send("uname=Dr.");
	}
</script>
</head>
<body>
	<input type="button" onclick="getText()" value="post方式">
	<a href="javascript:;" onclick="getText()">点击</a>
</body>
</html>