<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "Models.Normal"%>
<%@ page import = "Models.Advertise" %>
<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form>
	<%
		Normal user = (Normal) session.getAttribute("curUser");
	%>
	<!-- show profile picture -->
	
	Name: <%= user.getName() %>
	User Name: <%= user.getUserName() %>
	E-mail: <%= user.getEmail() %>
	Phone: <%= user.getEmail() %>
	
	<!-- show user advertises -->
	
	<%
			ArrayList<Advertise> advertises = user.getListOfAdvertises();
			for(int i=0 ; i<advertises.size() ; i++){}
	%>

	</form>

</body>
</html>