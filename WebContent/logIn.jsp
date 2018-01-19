<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<jsp:include page="navbar.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="bootstrap_slate.css">
 <link rel="stylesheet" href="style.css">
 <script src="jquery-2.1.4.min.js"></script>
<%
	if (request.getSession().getAttribute("message") != null){%>
	<script type="text/javascript">
			alert( "Error: Username Or Email is Wrong ");
	</script>
<%
request.getSession().removeAttribute("message");
	}%>

<title>HouseOfCard</title>
</head>
<body>
	<%
	
   HttpSession sessionn = request.getSession(false);
   Cookie[] cookies = request.getCookies();
  
   if(application.getAttribute("sessionManager") == null){
	   HashMap<String , HttpSession> sessionsManager = new HashMap<String , HttpSession>();
	   application.setAttribute("sessionManager", sessionsManager);
   }
   if(cookies == null) // is not created yet
   {
	  request.getSession(true);
	%>  
	<div id = "form"></div>
   <%
  
   }
  else {
	HashMap<String , HttpSession> sessions = ( HashMap<String , HttpSession> )application.getAttribute("sessionManager");
	if(sessions.isEmpty()){   // sessionManager doesn't exist
	  for (Cookie cookie : cookies) {
		  if(cookie.getValue().equals(request.getSession().getId())) { 
		    	cookie.setMaxAge(0);
		    	 response.addCookie(cookie);
		  }
	  }
	 %>      
      <div id ="form"></div>
	  <%
    }
	else{  // exist
	  Cookie myCookie = null;
	  for (int i = 0; i < cookies.length; i++) {
	    if (cookies [i].getName().equals ("sessionId")){
			myCookie = cookies[i];
			break;
	     } 
	  }
	// String id = myCookie.getValue();
	 HashMap<String , HttpSession> search = (HashMap<String , HttpSession>)application.getAttribute("sessionManager");
     HttpSession temp = search.get(request.getSession().getId());
     if(temp == null){    // No session associated with this cookie
       for (Cookie cookie : cookies) {
    	   if(cookie.getValue().equals(request.getSession().getId())) { 
		    	cookie.setMaxAge(0);
		    	 response.addCookie(cookie);
		  }
       }
    %>      
      <div id ="form"></div>    		
    <%
     }
     else{
    	   // go to the home view with his data
    	 RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
    	 dispatcher.forward(request, response);  
    	 
    	 }
      }  
    }
 %>
 
   <template id = "signin">  
	<div class = "container">
    <div class="modal-dialog" style = "margin-top:100px;" id = "LogIn">	
    <div class="modal-content">
      <div class="modal-header">
        <div class="modal-title"><center class = "register">Log In</center></div>
		</div>
		<br>
		<center>
    <div class ="row">
        <div class = "col-md-5 col-md-offset-1">
	  <form action="SessionController" method="GET">
	    
	  	   
	   
      <div class="input-group">
             <span class="input-group-addon" id = "sizing-addon2"><i>&#64;</i></span>
          <input type="text" class="form-control"  name = "userName" placeholder="UserName">
        </div>
	   
	  	<br>	   
	  <div class="input-group">
	  <span class="input-group-addon"><i>&#128273;</i></span>
          <input type="password" class="form-control" name = "password" placeholder="Password">
        </div>
	<br>
	 <button value="login" name="Submit" class="btn btn-primary btn-lg">Log In</button>
		<br><br> 
	  </form>
	 </div>
	  <div class ="col-md-3">
          <img class="MidImg" alt="" src="house1.png">
        </div>
	 </div>
	</center>
	</div>
	</div>
    </div>
   </template>
	
	
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
   	 var template = document.querySelector('#signin');
     var clone = document.importNode(template.content, true);
     var host = document.querySelector('#form');
     host.appendChild(clone);
	</script>
	
</body>
</html> 