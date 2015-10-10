<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="js/json.js"></script>
<script type="text/javascript">
	function json1() {
		var obj1 = {
			"name" : "zhangsan",
			"age" : "100"
		};
		alert(obj1.name + "  " + obj1.age);
	}
	function json2() {
		var obj2 = {
			"name" : "李四",
			"address" : {
				"born" : "earth",
				"area" : "south"
			}
		};
		document.getElementById("a").innerHTML = obj2.name + ":"
				+ obj2.address.born + ":" + obj2.address.area;
	}
	function json3() {
		var obj3 = [ {
			"name" : "王五"
		}, 1, "李四", [ 1, 2, 3 ] ];
		document.getElementById("b").innerHTML = obj3[1] + "---" + obj3[0].name
				+ "-----" + obj3[2] + "---" + obj3[3][0];
	}
	function json4() {
		//第一种方式
		var str = '{"name":"zhangsan","age":"1000"}';
		var i = document.getElementById("c").innerHTML;
		if (i == 0) {
			var ff1 = JSON.parse(str);
			document.getElementById("b").innerHTML = "方法1" + ff1.name + ":"
					+ ff1.age;
			document.getElementById("c").innerHTML = "1";
		} else if (i == 1) {//第二种方式
			var ff2 = str.parseJSON();
			document.getElementById("b").innerHTML = "方法2" + ff2.name + ":"
					+ ff2.age;
			document.getElementById("c").innerHTML = "2";
		} else if (i == 2) {
			//第三种方式
			var ff3 = eval(("(" + str + ")"));
			document.getElementById("b").innerHTML = "方法3" + ff3.name + ":"
					+ ff3.age;
			document.getElementById("c").innerHTML = "0";
		}

	}
	function json5() {
		var str = '[{"name":"zhangsan","age":"1000"},{"address":"waterplant"}]';
		var i = document.getElementById("c").innerHTML;
		//第一种方式
		if (i == 0) {
			var ff1 = str.parseJSON();
			document.getElementById("b").innerHTML = "方法1" + ff1[0].name + ":"
					+ ff1[0].age + ":" + ff1[1].address;
			document.getElementById("c").innerHTML = "1";
		} else {
			//第二种方式
			var ff2 = eval(("(" + str + ")"));
			document.getElementById("b").innerHTML = "方法2" + ff2[0].name + ":"
					+ ff2[0].age + ":" + ff2[1].address;
			document.getElementById("c").innerHTML = "0";
		}
	}
	function json6() {
		var str = {
			"name" : "zhangsan",
			"age" : "1000"
		};
		var i = document.getElementById("c").innerHTML;
		//第一种方式
		if (i == "0") {
			var ff1 = str.toJSONString();
			document.getElementById("b").innerHTML = "方法1" + ff1;
			document.getElementById("c").innerHTML = "1";
		} else {
			//第二种方式
			var ff2 = JSON.stringify(str);
			document.getElementById("b").innerHTML = "方法2" + ff2;
			document.getElementById("c").innerHTML = "0";
		}
	}
</script>
</head>
<body onload="json1()">
	<b id="c">0</b>
	<a href="javascript:;" id="a"></a>
	<b id="b"></b>
	<input onclick="json2()" value="点击" type="button">
	<input onclick="json3()" value="点击" type="button">
	<input onclick="json4()" value="JSON字符串-->JSON对象" type="button">
	<input onclick="json5()" value="JSON字符串-->JSON数组" type="button">
	<input onclick="json6()" value="JSON对象-->JSON字符串" type="button">
</body>
</html>
