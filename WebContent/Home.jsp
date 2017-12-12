<%@page import="java.util.*"%>
<%@page import="Models.Advertise"%>
<%@page import="DB.SelectFromDB"%>
<jsp:include page="navbar.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
 <link rel="stylesheet" href="bootstrap_slate.css">
 <link rel="stylesheet" href="style.css">
 <script src="jquery-2.1.4.min.js"></script>
 
<title>HouseOfCards</title>
</head>
<body>

  <div id="sidenav" class="sidenav">
    <a href="#" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="#"></a>
    <a href="#"></a>
  </div>
  <div class = "row"> 
  <button id = "menu" type="button" class="btn btn-default circle" onclick="openNav()">
    <span class = "fa"style = "font-weight:bold;">&#9776;</span>
  </button>
   
    <div id = "AddforUser" style = "float:right;"> 
    <a href = "AddAdvertise.jsp"><button class = "btn addAdv">Add Advertise </button></a>
    </div>
   </div>
    <br>    
    
   <%
    if (request.getAttribute("houses") == null){
    	RequestDispatcher rd= request.getRequestDispatcher("MainController?showHouses=showHouses");
		rd.forward(request,response);
      }
  
    else if(!((ArrayList<Advertise>)request.getAttribute("houses")).isEmpty() || (!((ArrayList<Advertise>)request.getAttribute("houses")).isEmpty())){

		ArrayList<Advertise> houses =(ArrayList<Advertise>) request.getAttribute("houses"); 
        if(houses.size() > 0 ){
        %>
        <div class = "container" id = "main">
         <div class = "row">
         <%
          for(int i = 0 ; i < houses.size() ; i++){
         %>
         
         <a href=<%= "\"showAdvertise.jsp?id=" + houses.get(i).getId() + "\"" %> >
          <div class = "col-md-3 col-sm-12">
           <div class = "thumbnail">
           <br>
             <!--  function ready in selectDB -->
            <img alt=""  src="house1.png">
            <b> <p class = "title"> <%= houses.get(i).getTitle() %></p></b>
            <ul>
            <li> Price: <%= houses.get(i).getPrice() %>$</li>
            <li> Rate: <%= houses.get(i).getRate()%></li>
            </ul>
           </div>
           </div>
           </a>
         <% 	
    	}
         %>
         </div>
         </div>
         <%
        }
       }
     %>
    
    
    
    
    
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
    	var session = <%= session.getAttribute("curUser") %>;
    	if(session != null){
          $("#AddforUser").show();	  
        }
       else{ 
       	$("#AddforUser").hide();
       }
    });
    
    function openNav() {
        document.getElementById("sidenav").style.width = "180px";
        document.getElementById("main").style.marginLeft = "50px";
      }

      function closeNav() {
        document.getElementById("sidenav").style.width = "0";
        document.getElementById("main").style.marginLeft = "0px";
      }	
    
    </script>
    
    
</body>
</html>
