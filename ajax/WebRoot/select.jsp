<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="js/my.js"></script>
<script type="text/javascript">
	function getCity(v1) {
		var xhr = getXhr();
		//匿名函数
		xhr.onreadystatechange = function() {
			if (xhr.status == 200 && xhr.readyState == 4) {
				var txt = xhr.responseText;
				var strs = txt.split(";");
				$("s2").innerHTML = "";
				for (var i = 0; i < strs.length; i++) {
					var s = strs[i].split(",");
					var op = new Option(s[0], s[1]);
					$("s2").options[i] = op;
				}
			}
		};
		xhr.open("get", "getCity.do?name=" + v1, true);
		xhr.send(null);
	}
</script>
</head>

<body>
	<select id="s1" onchange="getCity(this.value);" style="width: 120px">
		<option value="bj">北京</option>
		<option value="sh">上海</option>
		<option value="ah">安徽</option>
	</select>
	<select id="s2" style="width:120px"></select>
</body>
</html>
