<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script language="JavaScript">
  var timerID = null;
  var timerRunning = false;
  function stopclock(){
    if(timerRunning)
     clearTimeout(timerID);
    timerRunning = false;
}

function startclock(){
    stopclock();
    showtime();
}

function showtime(){
    var now = new Date();
    var hours = now.getHours();
    var minutes = now.getMinutes();
    var seconds = now.getSeconds();
    var timeValue = "" + ((hours > 12) ? hours - 12 : hours);
    timeValue  += ((minutes < 10) ? ":0" : ":") + minutes;
    timeValue  += ((seconds <	 10) ? ":0" : ":") + seconds;
    if (hours>=6 && hours<=12){
     timeValue  += ("上午");
    }
    if(hours>12 && hours<=18) 
       {timeValue += ("下午");}
    if(hours>18 && hours <=24)
       {timeValue +=("晚上");}
    if(hours<6) 
       {timeValue += ("深夜");}
    document.clock.face.value = timeValue ;
    timerID = setTimeout("showtime()",1000);
    timerRunning = true;
}
</script>

</head>

<body onLoad="startclock()">
	<form action="hello" method="get">
		<input type="text" name="a" value="aaaaa" /> <input type="submit"
			value="ok">
	</form>
	<form method="POST" name="clock">
		<p>
			<input type="text" name="face" size="11" style="font-size: 9pt">
		</p>
	</form>
</body>
</html>
