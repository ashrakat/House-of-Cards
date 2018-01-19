<%@page import="java.util.*"%>
<%@page import="Models.*"%>
<%@page import="DB.*"%>
<%@page import="Controllers.*"%>
<jsp:include page="navbar.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="bootstrap_slate.css">
<link rel="stylesheet" href="bootstrap-select.min.css">

<link rel="stylesheet" href="style.css">
<script src="jquery-2.1.4.min.js"></script>
<script src="bootstrap-select.min.js"></script>
<script>
function closeAdvertise(id){
	
	$.get("MainController", {"closeID": id}, function(result){
		$("#"+id).slideUp();
    });	
}

function SuspendAdvertise(id ,title , i ){
	$.get("MainController", {"suspendID": id , "index":i}, function(result){
        $("#"+id+title).mark();
    });	
}
</script>
<title>Active Advertises</title>
</head>

<body>
	 <div class='container' id='main'>
	    <div class='container'>
	       <div class='row'>
	       	<%
	    	ArrayList<Advertise> houses = new ArrayList<Advertise>  () ;
	       		if(request.getSession().getAttribute("houses") != null)
	       			houses = (ArrayList<Advertise>)request.getSession().getAttribute("houses"); 
	       		String path="" , state = "suspend" , close , suspend; 
	       		for(int i=0 ; i <houses.size() ; i++){
	       			path = houses.get(i).getId() + ".jpg";
	       			state = "suspend";
	       			close = "Close_"+houses.get(i).getId();
	       			suspend ="Suspend_"+houses.get(i).getId();
	       			if(houses.get(i).isSusbinded()) 
		            	  state="Unsuspend"; 
		            if(!(houses.get(i).isClose()) ){
	       	%>
	       		<div class="col-md-3 col-sm-12" id = <%=houses.get(i).getId()%>>
					<div class="thumbnail">
	       		      <a href=<%="\"showAdvertise.jsp?id=" + houses.get(i).getId() + "\""%>>
						<br>
						  <img src="<%=path%>" alt="" class="MidImg"> <b>
							<%if( houses.get(i).isSusbinded())%>
								<p class="title"> <mark><%=houses.get(i).getTitle()%> </mark></p>
						
							<%else if (!(houses.get(i).isSusbinded())) %>
								<p class="title">  <%=houses.get(i).getTitle()%> </p>
							</b>
							<ul>
								<li>ID: <%=houses.get(i).getId()%></li>
								<li>Price: <%=houses.get(i).getPrice()%>$</li>
								<li>Rate: <%=houses.get(i).getRate()%></li>
								<li>Suspend:<%=state%></li>
							</ul>
					 </a>
					<center><span>
					 <button class ="btn myAddAdvbtn btn-sm" value="<%=close%>" onclick="closeAdvertise('<%=houses.get(i).getId()%>')">Close</button> 
					 <button  class ="btn myAddAdvbtn btn-sm" value="<%=suspend%>" onclick="SuspendAdvertise('<%=houses.get(i).getId()%> ,<%=houses.get(i).getTitle()%>, <%=i%> ')">Suspend</button></span></center>
				</div>
			</div>
		<%}}%>
</body>

</html>