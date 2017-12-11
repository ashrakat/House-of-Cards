<%@page import="java.util.*"%>
<%@page import="Models.Advertise"%>
<%@page import="DB.SelectFromDB"%>
<jsp:include page="navbar.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
 <link rel="stylesheet" href="bootstrap_slate.css">
 <link rel="stylesheet" href="style.css">
 <script src="jquery-2.1.4.min.js"></script>
 
<title>HouseOfCards</title>
</head>
<body>

    
    <div id = "AddforUser" style = "float:right;"> 
    <button class = "btn addAdv" onClick = "addAdvertise();" >Add Advertise </button>
    </div>
    
        
    <div class = "container">
    <%
    if(request.getAttributeNames().toString().contains("houses") == true){
    	RequestDispatcher rd= request.getRequestDispatcher("MainController?showHouses=showHouses");
		rd.forward(request,response);
    }
    
    else{
     ArrayList<Advertise> houses =(ArrayList<Advertise>) request.getAttribute("houses"); 
     if(houses != null){
     for(int i = 0 ; i < houses.size() ; i++){
      %>
      <div class = "row">
        <div class = "col-md-3 col-sm-12">
         <img alt="" src="">
         <p> <%= houses.get(i).getForWhat() %></p>
         <p> <%= houses.get(i).getType() %></p>
         <p> <%= houses.get(i).getNumOfFloors() %></p>
         <p> <%= houses.get(i).getStatus() %></p>
         <p> <%= houses.get(i).getRate() %></p>
        </div>
      </div>
      <% 
      }
     }
    }
     %>
    
    </div>
    
    
    
    <%!
    String getUserName(HttpServletRequest request){
    	if(request.getSession().getAttribute("userName") == null)
    		return "";
    	else{
    		return (String)request.getSession().getAttribute("userName");
    	}
     }
    %>
    
    <script>
    $(document).ready(function(){
    	var session = <%= session.getAttribute("userName") %>;
    	if(session != null){
          $("#AddforUser").show();	  
        }
       else{ 
       	$("#AddforUser").hide();
       }
    });
    
    function addAdvertise(){
    	window.location.href = "AddAdvertise.jsp";
    }
    </script>
    
</body>
</html>