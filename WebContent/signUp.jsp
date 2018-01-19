<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
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
<title>HouseOfHouse</title>
<%
	if (request.getSession().getAttribute("message") != null){%>
	<script type="text/javascript">
			alert( "Error: Username Or Email is already Used");
	</script>
<%
request.getSession().removeAttribute("message");
}%>


<%
	if (request.getSession().getAttribute("Photo") != null){%>
	<script type="text/javascript">
			alert( "Error: your photo size is greater than max size");
	</script>
<%
request.getSession().removeAttribute("Photo");
	}%>
	
<script>
$(document).ready(function(){
    $("#username").keyup(function(){
        var txt = $(this).val();
        $.post("UserController", {suggest: txt}, function(result){
            $("#check").html(result);
        });
    });
    
    $("#email").keyup(function(){
        var txt = $(this).val();
        $.post("UserController", {mail: txt}, function(result){
            $("#checkmail").html(result);
        });
    });
});
</script>

<script>
      function checkRegistrationForm(form){
                
                if(form.phone.value != ""){
                	 if (form.phone.value.length != 11) {
                         alert("Error: Phone Number must contain exactly 11 characters!");
                         form.password.focus();
                         return false;
                     }
                	 if (form.phone.value[0] != '0' || form.phone.value[1] != '1' ||
                			 (form.phone.value[2] != '0' && form.phone.value[2] != '1' && form.phone.value[2] != '2') ) {
                         alert("Error: Phone Number must start with 010, 011, 012!");
                         form.password.focus();
                         return false;
                     }
                }

                if (form.password.value != "" ) {
                    if (form.password.value.length < 6) {
                        alert("Error: Password must contain at least six characters!");
                        form.password.focus();
                        return false;
                    }
                    re = /[0-9]/;
                    if (!re.test(form.password.value)) {
                        alert("Error: password must contain at least one number (0-9)!");
                        form.password.focus();
                        return false;
                    }
                    re = /[a-z]/;
                    if (!re.test(form.password.value)) {
                        alert("Error: password must contain at least one small character (a-z)!");
                        form.password.focus();
                        return false;
                    }
                    re = /[A-Z]/;
                    if (!re.test(form.password.value)) {
                        alert("Error: password must contain at least capital character (A-Z)!");
                        form.password.focus();
                        return false;
                    }
                }
                
                return true;
                
            }

</script>




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
	<div id="form"></div>
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
	  <div id="form"></div>
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
	<div id="form"></div>
	<%
	     }
	   else{
		 RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
		 dispatcher.forward(request, response); 
	     }  
	    }
	  }
	 %>
	 
	 
  <template id="signupin">
   <div class = "container">

    <div class="modal-dialog"  id = "signupIn">	
    <div class="modal-content">
      <div class="modal-header">
        <div class="modal-title"><center class = "register">Registeration</center></div>
		</div>
		<br>
	<center>
    <div class ="row">
        <div class = "col-md-5 col-md-offset-1">
	  <form onsubmit= "return checkRegistrationForm(this);"action="UserController" method="POST" enctype="multipart/form-data">
	    <div class="input-group">
			<span class="input-group-addon input-group-sm"><i class = "fa fa-user fa-lg"></i></span>
          <input type="text" class="form-control" name = "name" placeholder="FullName" required>
        </div>
	  	 <br>   
	   
		 <div class="input-group">
             <span class="input-group-addon" id = "sizing-addon2"><i>&#64;</i></span>
          <input type="text" id="username" class="form-control"  name = "userName" placeholder="UserName" required>
          <div id="check"><p></p></div>
        </div>
	     <br>  
	   
		 <div class="input-group">
			<span class="input-group-addon"><i class = "fa fa-mobile fa-lg"></i></span>
          <input type="number" class="form-control" name = "phone" placeholder="phone">
        </div>
         <br>  
	   
		 <div class="input-group">
			<span class="input-group-addon"><i class = "fa fa-envelope-o fa-lg"></i></span>
          <input type="email" id="email" class="form-control" name = "email" placeholder="Email" required>
           <div id="checkmail"><p></p></div>
        </div>
         <br>  
	  		   
	   <div class="input-group">
			<span class="input-group-addon"><i>&#128273;</i></span>
          <input type="password" class="form-control" name = "password" placeholder="Password">
        </div>
        <br>
       
       <div>Max Size = 45KB </div>
       <div class="input-group">
       	 <span class="input-group-addon"><i class = "fa fa-file-image-o"></i></span>
		 <input type="file" value="Upload File" name="pic" >
		</div>
	    <br>
	    
		  <br>
		<button value="signUp" name="SignUp" class="btn btn-primary btn-lg">Sign up</button>
		 
	  </form>
			
	  <p style= "color:yellow;">Already have an account?!</p>
	  <a href="logIn.jsp"><button class="btn btn-primary btn-lg"> Log In</button></a>
     <br><br><br>       
	 </div>
        <div class ="col-md-3">
          <img  alt="" src="house1.png">
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
   var template = document.querySelector('#signupin');
   var clone = document.importNode(template.content, true);
   var host = document.querySelector('#form');
   host.appendChild(clone);
 </script>

</body>
</html>