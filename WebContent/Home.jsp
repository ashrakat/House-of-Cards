<%@page import="java.util.*"%>
<%@page import="Models.Advertise"%>
<%@page import="DB.SelectFromDB"%>
<%@page import="Models.User"%>
<%@page import="Controllers.MainController"%>
<jsp:include page="navbar.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
 <link rel="stylesheet" href="bootstrap_slate.css">
 <link rel="stylesheet" href="bootstrap-select.min.css">

 <link rel="stylesheet" href="style.css">
 <script src="jquery-2.1.4.min.js"></script>
 <script src = "bootstrap-select.min.js"></script>

<title>HouseOfCards</title>

</head>
<body>

	<div id="sidenav" class="sidenav">
		<a href="#" class="closebtn" onclick="closeNav()">&times;</a> 
		<form action = "MainController" method = "POST">
		<center>
		 <div style = "padding-top:40px;padding-bottom:30px;">	
		  <div class="form-group">
          <label for="filter_Address">Desired Address:</label>
          <input type = "text" class = "range_value" name = "Address" required>
         </div>
        
		  <div class="form-group">
         <label for="filter_type">Type:</label>
         <br>
         <input type = "text" class = "range_value" name = "type" required>
        </div>
        
         <div class="form-group">
         <label for="filter_size">min Size:</label>
          <br>
         <input type = "text" class = "range_value" name = "size" required>
        </div>
        
         <div class="form-group">
         <label for="filter_Price">max Price:</label>
          <br>
          <input type = "text" class = "range_value" name = "price" required>
        </div>
        
         <div class="form-group">
         <label for="filter_floors">min numOfFloors:</label>
         <input type = "text" class = "range_value" name = "#ofFloor" required>
        </div>
        
         <div class="form-group">
         <label for="filter_rate">min Rate:</label>
          <br>
         <input type = "text" class = "range_value" name = "rate">
        </div>
        
		 <!--  <select name="filterby" class = "filterOption">
			<option value="Address">Address</option>
			<option value="Type">Type</option>
			<option value="size">Size</option>
			<option value = "#ofFloor">#of floors</option>
			<option value = "Price">Price</option>
			<option value = "Rate">Rate</option>
		  </select>
		  --> 
		 </div>
		 <!--  
		 <center>
		 <div class="input-group filtervalue">
			<span class="input-group-addon input-group-sm"><i class = "fa fa fa-search fa-md"></i></span>
          <input type="text" class="form-control" name = "value" placeholder ="filterValue">
         </div>
          <br>
        -->
		 <input type="submit" class="btn btn-warning" value = "Filter" name = "filteration">
	    </center>
		</form> 
	</div>
	
	<div class="row">
		<button id="menu" type="button" class="btn btn-default circle"
			onclick="openNav()">
			<span class="fa" style="font-weight: bold;">&#9776;</span>
		</button>
		 <%
		 String add= "" ;
		 String URL = "";
		 String active = "";
		 String activeUrl="";
		 if(request.getSession().getAttribute("curUser") != null){
		   User user = (User) request.getSession().getAttribute("curUser");
			 if(user.getType().equals("Normal")){
				 add = "Add Advertise" ; 
				 URL = "AddAdvertise.jsp";
			 }
			 else{
				 add =  "Active Advertise" ;
			      URL = "allActiveAdvertises.jsp";
			      active ="All Users";
			      activeUrl = "AllUsers.jsp";
			 }
		 
		 %>
		<div id="AddforUser" style="float: right;">
		    <span><a href="<%=URL%>"><button class="btn addAdv"><%=add%></button></a></span>
		    <% 
		    
		    if(user.getType().equals("Admin")){ %>
			<span>
			 <a href="<%=activeUrl%>">
			  <button class="btn addAdv"><%=active%></button>
			 </a>
			</span>
			<% 
			} }
			%>
		</div>
	</div>
	<br>

	<%
		if (request.getServletContext().getAttribute("mainController") == null) {
			request.getServletContext().setAttribute("mainController", new MainController());
		}

	if (request.getSession().getAttribute("houses") == null){
    	RequestDispatcher rd= request.getRequestDispatcher("MainController?showHouses=showHouses");
		rd.forward(request,response);
      }
	
	ArrayList<Advertise> houses = new ArrayList<Advertise>  () ;
	
	if (request.getAttribute("houses") != null){
		houses = (ArrayList<Advertise>) request.getAttribute("houses");
		request.removeAttribute("house");
	}
	else if (request.getSession().getAttribute("houses") != null){
		houses = (ArrayList<Advertise>) request.getSession().getAttribute("houses");
	}
    if (houses.size() > 0) {
	%>
	<div class="container" id="main">
		<div class="container">
			<div class="row">
				<%
					String path = "";
							for (int i = 0; i < houses.size(); i++) {
								if(!(houses.get(i).isSusbinded() || houses.get(i).isClose())){
								path = houses.get(i).getId() + ".jpg";
				%>

				<a
					href=<%="\"showAdvertise.jsp?id=" + houses.get(i).getId() + "\""%>>
					<div class="col-md-3 col-sm-12">
						<div class="thumbnail">
							<br>
							<!--  function ready in selectDB -->
							<img src="<%=path%>" alt="" class="MidImg"> <b>
								<p class="title">
									<%=houses.get(i).getTitle()%></p>
							</b>
							<ul>
								<li>Price: <%=houses.get(i).getPrice()%>$
								</li>
								<li>Rate: <%=houses.get(i).getRate()%></li>
							</ul>
						</div>
					</div>
				</a>
				<%
					}}
				%>
			</div>
		</div>
		<%
			}
			
		%>

	</div>



	<%!String getUserName(HttpServletRequest request) {
		if (request.getSession().getAttribute("curUser") == null)
			return "";
		else {
			User user = (User) request.getSession().getAttribute("curUser");
			return user.getUserName();
		}

	}%>

	<script>
		$(document).ready(function() {
			var session =
	       <%=session.getAttribute("curUser")%>;
			if (session != null) {
				$("#AddforUser").show();
			} else {
				$("#AddforUser").hide();
			}
		});

		function openNav() {
			document.getElementById("sidenav").style.width = "160px";
			// document.getElementById("main").style.marginLeft = "120px";
		}

		function closeNav() {
			document.getElementById("sidenav").style.width = "0";
			// document.getElementById("main").style.marginRight = "-10";
		}
	</script>


</body>
</html>
