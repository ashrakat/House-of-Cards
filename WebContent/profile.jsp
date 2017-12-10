<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "Models.User"%>
<%@ page import = "Models.Normal"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form>
	<%
		User user = (Normal) session.getAttribute("curUser");
	%>
	<!-- show profile picture -->
	
	Name: <%= user.getName() %>
	User Name: <%= user.getUserName() %>
	E-mail: <%= user.getEmail() %>
	Phone: <%= user.getEmail() %>
	
	<button value="Show My Properties" action="showAllAdvertises.jsp"></button>
	</form>

</body>
</html>