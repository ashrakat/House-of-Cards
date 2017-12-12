<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Models.Normal"%>
<%@ page import="Models.Advertise"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.io.*"%>
<jsp:include page="navbar.jsp"></jsp:include>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="bootstrap_slate.css">
<link rel="stylesheet" href="style.css">
<script src="jquery-2.1.4.min.js"></script>
<title>HouseOfHouse</title>
</head>
<body>

	<form>
		<%
			Normal user = (Normal) session.getAttribute("curUser");
		%>
		<%
			String path = user.getUserName() +".jpg";
		%>
		<!-- show profile picture -->

		Name:
		<%=user.getName()%>
		<br> <br> User Name:
		<%=user.getUserName()%>
		<br> <br> E-mail:
		<%=user.getEmail()%>
		<br> <br> Phone:
		<%=user.getPhone()%>
		<br> <br> <img src="<%=path%>"  width="300" height="200"
		   alt="<%=user.getPhone()%>"> 
		<br> <br> Path:
		<%=path%>
		<!-- show user advertises -->

		<%
			ArrayList<Advertise> advertises = user.getListOfAdvertises();
			for (int i = 0; i < advertises.size(); i++) {
			}
		%>

	</form>

</body>
</html>