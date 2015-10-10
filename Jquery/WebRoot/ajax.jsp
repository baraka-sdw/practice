<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/qrcode.min.js"></script>
<script type="text/javascript" src="js/spin.js"></script>
<script type="text/javascript">
	//function f1() {
		//$("#d1").load("getInfo.do", {
			//"name" : "张三"
		//});
	//}
	$(function() {
     $("#d1").load("getInfo.do", {
			"name" : "张三"
		});
	})
</script>
<style type="text/css">
</style>
</head>
<body>
	<div id="d1"></div>
	<input id="b1" value="b1" type="button" onclick="f1()">
</body>
</html>
