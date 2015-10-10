<%
	Object obj = session.getAttribute("user");
	if(obj == null){
		response.sendRedirect("login.jsp");
	}
 %>