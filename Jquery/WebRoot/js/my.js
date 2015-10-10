function getXhr() {
	var xhr=null;
	xhr=new XMLHttpRequest();
//	xhr=window.XMLHttpRequest?new XMLHttpRequest():new ActiveXObject("Microsoft.XMLHttp");
	 return xhr;
}

function  $(id){
	return document.getElementById(id);
	
}

function $F(id) {
	return $(id).value;
}