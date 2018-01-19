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

<script>
	$(document).ready(function(){
	    $("button").click(function(){
            var btn_val = $(this).val();
			var txt = $("input").val();
			//var txt = -6;
			$.post("MainController", {suggest:txt , event:btn_val}, function(result){
	            $("#form").html(result);
	        });
	    });	    
	});
	
	$(document).ready(function(){
		var txt = -1;
		$.post("MainController", {suggest:txt}, function(result){
            $("#form").html(result);
        });
	});
	

</script>

<title>Active Advertises</title>
</head>

<body>

 <% if (request.getSession().getAttribute("curUser") != null){
       User user = (User)request.getSession().getAttribute("curUser") ; 
	   if(user.getType().equals("Admin")){	 
	 %>
	 
<p class = "title" style ="text-align:center;">Enter id for Advertise You want Suspend :</p>
<center>
  <input type="text" placeholder = "Enter ID">
  <br><br>
  </center>
  <center>
    <span></span> <button class = "btn myAddAdvbtn btn-sm" value="Suspend" >Suspend/Unsuspend</button>
    <span> </span> <button class = "btn myAddAdvbtn btn-sm" value="Close"  >Close</button>
 </center>
 <br>
<form id="form">

</form>
<%  }} %>

</body>

</html>