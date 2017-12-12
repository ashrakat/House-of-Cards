<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Models.User"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
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
		  <li><a href="UserView.jsp"><%= getUserName(request) %></a></li>
         <li><a href="signUp.jsp">Signin/up</a></li>
          <li><a href="SessionController?logout=logout">Logout</a></li>
         </ul>
        </div>
    </div>
   </nav>
    
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
    
</body>
</html>