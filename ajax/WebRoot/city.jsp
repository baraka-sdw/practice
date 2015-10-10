<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="js/my.js"></script>
<script type="text/javascript" src="js/json.js"></script>
<script type="text/javascript">
	function getCity(v1) {
		var xhr = getXhr();
		//匿名函数
		xhr.open("post", "city.do", true);
		xhr.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.status == 200 && xhr.readyState == 4) {
				var txt = xhr.responseText;
				var citys=txt.parseJSON();
				$("s2").innerHTML = "";
				for (var i = 0; i < citys.length; i++) {
					var op = new Option(citys[i].cityName,citys[i].cityValue);
					$("s2").options[i] = op;
				}
			}
		};
		xhr.send("name="+v1);
	}
</script>
</head>

<body>
	<select id="s1" onchange="getCity(this.value);" style="width: 120px">
		<option value="bj">北京</option>
		<option value="ah">安徽</option>
	</select>
	<select id="s2" style="width:120px"></select>
</body>
</html>
