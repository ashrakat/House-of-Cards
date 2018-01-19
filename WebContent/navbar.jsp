<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Models.User"%>    
<%@page import="Models.Normal"%>    
<%@page import="Models.Notification"%>    
<%@page import="java.util.ArrayList"%>   
<%@page import="DB.SelectFromDB"%>    
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

<title>Insert title here</title>
</head>

 <%!
    String getUserName(HttpServletRequest request){
    	if(request.getSession().getAttribute("curUser") == null)
    		return "";
    	else{
    		User user = (User)request.getSession().getAttribute("curUser");
    		return user.getUserName();
    	}
    }
%>

<script>
		var clicked = true;
		function viewNotifications(){
			if (clicked == true) {
				document.getElementById('notifydata').style.display = "block";
			} else {
				document.getElementById('notifydata').style.display = "none";
			}
			clicked = !clicked;
		}
</script>
    
<body>
 <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            
            <a class="navbar-brand" href="Home.jsp" ><img  src="house1.png"></a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
       
		 <ul class="nav navbar-nav navbar-right">
		  <%if(getUserName(request).equals("")){
		  %>
		  		<li><a href="signUp.jsp">SignUp/LogIn</a></li>
		  <%}else{
		  %>
		  	   <li  title="Notifications" onclick="viewNotifications()">
		  	   
		        <a href="#" class="dropdown-toggle">
         		 	<i class="fa fa-bell-o"></i>
                </a>
                  <%
                  User user = (User) request.getSession().getAttribute("curUser");
                  if(user != null)	
                  if (user.getType().equals("Normal")) {%>
                 	<ul class="dropdown-menu" role="menu" id = "notifydata">
                 		
                        <%
                        ArrayList<Notification> notifications = null; 
                        notifications = SelectFromDB.allUserNotfication(user);
                		System.out.println("my notifications = " + notifications.size());
                          for(int i=0; i<notifications.size(); i++){
                        %>
	                    <li>
	                        <a href=<%= notifications.get(i).getLink()%>><%= notifications.get(i).getBody() %></a>
	                    </li>
                        <%} %>
                        
                      <a href = "#"><div class ="seeAll">See All</div></a>

                   </ul> 
                   <%} %>
                </li>               
                <li><a href="profile.jsp"><%= getUserName(request) %></a></li>
          		<li><a href="SessionController?logout=logout">Logout</a></li>
            </ul>
          <%} %>
          
        </div>
    </div>
   </nav>
    
</body>
</html>
