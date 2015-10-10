<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<script type="text/javascript" src="js/my.js"></script>
<script type="text/javascript" src="js/json.js"></script>
<script type="text/javascript">
	function f() {
		var xhr = getXhr();
		xhr.open("post", "sale.do", true);
		xhr.setRequestHeader('content-type',
				'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var txt = xhr.responseText;
				var sales = txt.parseJSON();
				var salesInfo = "当前销量最好的商品是:" + "<br/>";
				for (var i = 0; i < sales.length; i++) {
					salesInfo += "商品名称:" + sales[i].prodName + "销量:"
							+ sales[i].qty + "<qty>" + "<br/>";
				}
				$("d1").innerHTML = salesInfo;
			}
		};
		xhr.send(null);
		setTimeout("f()", 2000);
	}
</script>
</head>
<body onload="f()">
	<div id="d1"></div>
	<input type="button" onclick="f()">
</body>
</html>
