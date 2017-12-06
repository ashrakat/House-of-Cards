<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="styles/bootstrap.min.css">
<title>HouseOfCards</title>
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
           <!-- <a class="navbar-brand" href="#" ><p style ="color:gray;" th:if="${user.username}" th:inline="text"> [[${user.username}]]  </p> </a> -->
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href="Home.jsp">Home</a></li>
              <li><a href="UserView.jsp">profile</a></li>
            </ul>
		 <ul class="nav navbar-nav navbar-right">
         <li><a href="logInUp.jsp">Signin/up</a></li>
         <li><a href="#">Sign out</a></li>
         </ul>
        </div>
       </div>
    </nav>
</body>
</html>