<%@page import="java.util.*"%>
<%@page import="Models.*"%>
<%@page import="DB.*"%>
<%@page import="Controllers.*"%>
<jsp:include page="navbar.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="bootstrap_slate.css">
<link rel="stylesheet" href="bootstrap-select.min.css">

<link rel="stylesheet" href="style.css">
<script src="jquery-2.1.4.min.js"></script>
<script src="bootstrap-select.min.js"></script>

<script >
function changePassword(username){
	var txt = $("#"+username).val();
	$.get("UserController", {"username": username , "newpass":txt}, function(result){
		alert("Succesfuly Changed");
    });
	
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Users </title>
</head>
<body>
 
 <%
 //ArrayList<User> users = (ArrayList<User>)request.getSession().getAttribute("allUsers");
 %>
 <% if (request.getSession().getAttribute("curUser") != null){
       User user = (User)request.getSession().getAttribute("curUser") ; 
	   if(user.getType().equals("Admin")){	 
	 %>
 
 <form >

    <div class="container" id="main">
	     <div class="container">
	         <div class="row">
	         <%
	         ArrayList<Normal> users = (ArrayList<Normal>)SelectFromDB.allUsers();
	         	String path = ""; 
	         	for(int i =0 ; i < users.size() ; i++){
	         		if(users.get(i).getType().equals("Normal")){
	         			path = users.get(i).getUserName()+".jpg";
	         %>	
	         	 
					    <div class="col-md-4 col-sm-12">
						  	<div class="thumbnail"> <br> 
							 <img src="<%=path%>" alt="" class="MidImg"> <b>
								 <center> <p class="title"> <%=users.get(i).getUserName()%></p> </center> 
							      </b><ul> 
									 <li>Full Name: <%=users.get(i).getName()%></li>
									<li>Email: <%=users.get(i).getEmail()%></li> 
									<li>Phone: <%=users.get(i).getPhone()%></li>
								 </ul>
								 <center>
								   <div class="input-group" style = "margin-left:35px;">
	                               <span class="input-group-addon"><i>&#128273;</i></span>
                                    <input type="password" class="form-control" style = "max-width:200px;"  
                                     id="<%=users.get(i).getUserName()%>" placeholder="Enter the new Password">
                                   </div>
                                   <br> 
                                   <button class="btn myAddAdvbtn" onclick="changePassword('<%=users.get(i).getUserName()%>')" >Change</button>
                                   </center>
							</div>
						</div>
				<%
	         		}}
				%>		

</form>
<%  }} %>
</body>
</html>