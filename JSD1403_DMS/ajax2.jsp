<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="type=text/html;charset=utf-8">
<script type="text/javascript">
function getXhr() {
	var xhr=null;
	if(window.XMLHttpRequest){
	   xhr=new XMLHttpRequest();
	}else{
	   xhr=new ActiveXObject();
	}
	return xhr;
}
	function getText() {
		var xhr = getXhr();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				alert(xhr.responseText);
			}
		};
		xhr.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
		xhr.open("post", "post_text.do", true);
		xhr.send("uname=Dr.");
	}
</script>
</head>
<body>
	<input type="button" onclick="getText()" value="post方式">
	<a href="javascript:;" onclick="getText()">点击</a>
</body>
</html>