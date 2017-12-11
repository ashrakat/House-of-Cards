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

    
    <div id = "AddforUser" style = "float:right;"> 
    <button class = "btn addAdv" onClick = "addAdvertise();" >Add Advertise </button>
    </div>
    
        
    <div class = "container">
<<<<<<< Updated upstream
   <%
    if (request.getAttribute("houses") == null){
    	RequestDispatcher rd= request.getRequestDispatcher("MainController?showHouses=showHouses");
		rd.forward(request,response);
      }
  
    else if(!((ArrayList<Advertise>)request.getAttribute("houses")).isEmpty() || (((ArrayList<Advertise>)request.getAttribute("houses")).isEmpty())){
=======
    <%
    if (request.getAttribute("houses") == null){
    	RequestDispatcher rd= request.getRequestDispatcher("MainController?showHouses=showHouses");
		rd.forward(request,response);
		}
  
    else if(!((ArrayList<Advertise>)request.getAttribute("houses")).isEmpty() || (!((ArrayList<Advertise>)request.getAttribute("houses")).isEmpty())){
>>>>>>> Stashed changes
		ArrayList<Advertise> houses =(ArrayList<Advertise>) request.getAttribute("houses"); 
        if(houses.size() > 0 ){
        for(int i = 0 ; i < houses.size() ; i++){
         %>
<<<<<<< Updated upstream
         <div class = "row">
           <div class = "col-md-3 col-sm-12">
            <img alt="" src="">
            <p> <%= houses.get(i).getForWhat() %></p>
            <p> <%= houses.get(i).getType() %></p>
            <p> <%= houses.get(i).getNumOfFloors() %></p>
            <p> <%= houses.get(i).getStatus() %></p>
            <p> <%= houses.get(i).getRate() %></p>
           </div>
=======
         <div class = "container">
         <div class = "row">
         <a href=<%= "\"showAdvertise.jsp?id=" + houses.get(i).getId() + "\"" %> >
          <div class = "col-md-3 col-sm-12">
           <div class = "thumbnail">
           <br>
             <!--  function ready in selectDB -->
            <img alt=""  src="house1.png">
            <br>
            <ul>
            <li><b> <p class = "title"> <%= houses.get(i).getTitle() %></p></b></li>
            <li> <%= houses.get(i).getType() %></li>
            <li> <%= houses.get(i).getRate()%></li>
            </ul>
           </div>
           </div>
           </a>
         </div>
>>>>>>> Stashed changes
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