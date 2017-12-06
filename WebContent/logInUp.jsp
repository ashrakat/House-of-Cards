<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
	try{
	 Class.forName("com.mysql.jdbc.Driver");
	}
	catch(ClassNotFoundException e){
		e.printStackTrace();
	}
	String url = "jdbc:mysql://localhost:3306/accounts";
    String user = "root";
    String password = "shosho";
    Connection Con = null;
    Statement Stmt = null;
    ResultSet RS = null;
    try
    {
      Con = DriverManager.getConnection(url, user, password);
      Stmt = Con.createStatement();
    }
    catch(Exception e){
    	System.err.print(e);
   }
  
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
    <form action  = "SessionController" method = "POST"> 
    <input type = "text" name = "name" placeholder = "name">
    <br>
    <input type = "text" name = "userName" placeholder = "userName">
     <br>
    <input type = "number" name = "phone" placeholder = "phoneNumber"> 
     <br>
     <input type = "email" name = "email"  placeholder = "Email">
      <br>
     <input type = "password" name = "password" placeholder = "Password">
      <br>
     <button value = "signUp" name = "SignUp">Sign up</button>
    </form>
    <p>Already have an account</p>
    <button value = "signUp" name = "signUp" onCLick = '/logIn.jsp'>log in </button>
    <%
   }
   
   else{
	HashMap<String , HttpSession> sessions = ( HashMap<String , HttpSession> )application.getAttribute("sessionManager");
	if(sessions.isEmpty()){   // sessionManager doesn't exist
		  for (Cookie cookie : cookies) {
			  if(cookie.getValue().equals(request.getSession().getId())) { 
			    	cookie.setMaxAge(0);
			    	 response.addCookie(cookie);
			  }
		  }
		  %>      
	  <form action  = "SessionController" method = "POST"> 
        <input type = "text" name = "name" placeholder = "name">
        <br>
        <input type = "text" name = "userName" placeholder = "userName">
        <br>
        <input type = "number" name = "phone" placeholder = "phoneNumber"> 
        <br>
        <input type = "email" name = "email"  placeholder = "Email">
        <br>
        <input type = "password" name = "password" placeholder = "Password">
        <br>
        <button value = "signUp" name = "SignUp">Sign up</button>
      </form>
      <p>Already have an account</p>
      <button value = "logIn" name = "logIn" onCLick = '/logIn.jsp'>log in </button>
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
	   <form action  = "SessionController" method = "POST"> 
        <input type = "text" name = "name" placeholder = "name">
        <br>
        <input type = "text" name = "userName" placeholder = "userName">
        <br>
        <input type = "number" name = "phone" placeholder = "phoneNumber"> 
        <br>
        <input type = "email" name = "email"  placeholder = "Email">
        <br>
        <input type = "password" name = "password" placeholder = "Password">
        <br>
        <input type = "submit" value = "signUp" name = "SignUp">
      </form>
      <p>Already have an account</p>
      <button value = "logIn" name = "logIn" onCLick = '/logIn.jsp'>log in </button>
	    	<%
	     }
	   else{
		   RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
		   dispatcher.forward(request, response); 
	      }  
	    }
	  }
	 %>
	 
  
</body>
</html>