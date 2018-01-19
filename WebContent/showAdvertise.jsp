<%@page import="java.util.ArrayList"%>
<%@ page import="Models.Advertise"%>
<%@page import="DB.SelectFromDB"%>
<%@page import="Controllers.*"%>
<%@page import="Models.*"%>
    <%@page import="java.text.DateFormat" %>
<%@page import = "java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="navbar.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="bootstrap_slate.css">
<link rel="stylesheet" href="style.css">
<script src="jquery-2.1.4.min.js"></script>
<script src="bootstrap.min.js"></script>
<title>HouseOfCards</title>
</head>

<script type="text/javascript">
	var geocoder;
	var map;

	function initialize() {
		geocoder = new google.maps.Geocoder();
		var mapOptions = {
			zoom : 8,
			center : new google.maps.LatLng(29.966339, 31.2541814)
		}
		map = new google.maps.Map(document.getElementById('map'), mapOptions);
	}

	function codeAddress(address) {

		address = "Egypt, " + address;
		geocoder.geocode({
			'address' : address
		}, function(results, status) {
			if (status == 'OK') {
				map.setCenter(results[0].geometry.location);
				var curMarker = new google.maps.Marker({
					map : map,
					position : results[0].geometry.location,
					title : address
				});
				map.setZoom(15);
				map.panTo(curMarker.position);
			} else {
				alert('Geocode was not successful for the following reason: '
						+ status);
			}
		});
	}
</script>


<body onload="initialize()">
	<%
		System.out.println("getParameter :" + request.getParameter("id"));
		int id = 0;
		if (request.getParameter("id") != null)
			id = Integer.parseInt(request.getParameter("id"));
		
		Advertise advertise = SelectFromDB.getCertainAdvertiseInfo(id);
       	request.getSession().setAttribute("URL", request.getRequestURL()+"?id="+request.getParameter("id"));

	%>
	<p>
		<center  class="singleShowTitle"><%=advertise.getTitle()%></center>
		<center>
	    <%for (int i = 0 ; i < 5 ; i++){  %> 	
          <a href='AdvertiseController?id=<%=advertise.getId()%>&rateValue=<%=i+1%>'>
           <span class="fa fa-star-o fa-lg starRate"></span>
          </a>
        <%}%>	
        </center>
	<div class = "row"> 
	   <button type="button" class="btn addAdv" id = "editMyAdv" data-toggle="modal"  style = "float:right;" data-target="#editAdv">
	     edit Advertise
	   </button>
     </div>
     </p>
     <br>

	<%
		//show photos
		String main = advertise.getId() + ".jpg";
	%>

	<div class="container">
		<div class="row">
			<div class="col-md-1">
				<button class="btn btn-success btn-sm" id="backward">&lt;</button>
			</div>
			<div class="col-md-10">
				<div class="thumbnail">
					<br> <img id="bigImage" class="mainImg" src="<%=main%>" alt="" />
				</div>
			</div>
			<div class="col-md-1">
				<button class="btn btn-success btn-sm" id="forward">&gt;</button>
			</div>
		</div>

		<div class="row">
			<%
				int len = advertise.getOtherPhotos().size();
				String path = "";
				/*advertise.getOtherPhotos().length; */
				for (int i = 0; i < len; i++) {
					path = advertise.getId() + "-" + i + ".jpg";
			%>
			<div class="col-md-4">
				<div class="thumbnail">
					<br> <img class="otherImg" src="<%=path%>" alt="">
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>


	<div class="row">
		<div class="col-md-6 col-sm-12">
			<div class="thumbnail thumb">
				<ul>
					<li class="singleShow"><span class="declareAttr">Description:</span>
						<%=advertise.getDescription()%></li>
					<br><br>
					<li class="singleShow"><span class="declareAttr"> Type:
					</span><%=advertise.getType()%></li>
					<br><br>
					<li class="singleShow"><span class="declareAttr">
							Status: </span><%=advertise.getStatus()%></li>
					<br><br>
					<li class="singleShow"><span class="declareAttr"> For
							What: </span><%=advertise.getForWhat()%></li>
					<br><br>
					<li class="singleShow"><span class="declareAttr">
							Number Of Floors: </span><%=advertise.getNumOfFloors()%></li>
					<br><br>
					
						<li class="singleShow"><span class="declareAttr">
						  Rate: </span><%=advertise.getRate()%></li>
					<br><br>
					
					<li class="singleShow"><span class="declareAttr"> Size:
					</span><%=advertise.getSize()%>m2</li>
					<br><br>
					<li class="singleShow"><span class="declareAttr">Address:
					</span><%=advertise.getAddress()%></li>
					<br><br>
					<button class="btn" id="contactInfoButton" onClick="viewContact()">Contact
						Info</button>
					<br>

					<%
						//show user contact info
					%>
					<div id="contactInfo">
					 <div class="row">
					  <div class="col-md-3">
						<ul>
						 <li><span class="declareUserAttr"> Name: </span><%=advertise.getOwner().getUserName()%></li>
						 <li><span class="declareUserAttr"> Email:</span> <%=advertise.getOwner().getEmail()%></li>
						 <li><span class="declareUserAttr"> Phone: </span><%=advertise.getOwner().getPhone()%></li>
						</ul>
					  </div>
					 </div>
				   </div>
				</ul>
			</div>
		</div>

		<%
			//show user contact info
		%>
		<div class="col-md-6 col-sm-12" style="margin-left: -10px;">
			<div class="thumbnail thumb">
				<center>
					<div id="map" style="width: 320px; height: 510px;"></div>
					<br> <input type="button" class="btn myAddAdvbtn"
						value="show address on map"
						onclick="codeAddress('<%=advertise.getAddress()%>')">
				</center>
			</div>
		</div>
	</div>

	<%
		// List of comments
	%>
	<div class="row">
		<div class="col-md-12">
			<div class="thumbnail">
			 <p class="title" style="font-size: 24px;">Comments</p>
			  <%
				int commentsSize = advertise.getComments().size();
			    ArrayList<Comment> comments = SelectFromDB.allAdvertiseComment(advertise);
				for (int i = 0; i < comments.size(); i++) {
                     DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");				
				    String Date = dateFormat.format(comments.get(i).getDate());
			  %>
			<ul>
				<li><span class="declareUserAttr"> 
				<a class = "declareAttr" style = "font-size:18px;text-decoration:none;"
				    href=<%="\"profile.jsp?username=" + comments.get(i).getOwner().getUserName() + "\""%>">
					<%=comments.get(i).getOwner().getUserName()%>:
				</a>
				</span><%=comments.get(i).getBody()%>  &emsp;&emsp;&emsp;&emsp;&emsp;<%=Date%> </li>
			</ul>
			<%
			}
			%>
			<form action = "AdvertiseController" method = "GET">
			<input name = "id" value = <%= advertise.getId() %> style = "display:none;">
			<div class = "input-group">
			 <span class = "input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
			 <input type = "text" class = "form-control" style ="width:600px;" name = "comment" 
			 placeholder = "Add Comment ..!" >
			 <span><input type = "submit" id = "commentOwner" class = "btn btn-md myAddAdvbtn" value = "Add Comment"
			 name = "Add Comment"></span>
			</div>
			</form>
			</div>
		</div>
	</div>



    <!-- Model for Edit Adv form  -->
 <div id="editAdv" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        
        <center><h4 class="modal-title title" style = "font-size: 24px;">Edit Advertise </h4></center>
      </div>
      
       <form action="AdvertiseController" method="POST" enctype="multipart/form-data">
      <input type = "text" name = "id"   style ="display:none;" value =<%=advertise.getId()%>>
       <div class="input-group">
       <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
	    <input class="form-control" type="text" name="title" value = <%=advertise.getTitle()%>> 
	   </div>
	   <br>
	   
	   <div class="input-group">
	    <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
	    <input type="text" class="form-control" name="description" value =<%=advertise.getDescription()%>>
	   </div> 
	   <br> 
	  
	   
	    
	   <div class="input-group">
	    <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
	    <select class="form-control" name="status">
			<option>Finished</option>
			<option>Half Finished</option>
			<option>I know what you did last summer</option>
			</select>
		</div>
		<br>
		
		<div class="input-group">
		 <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
	     <select class="form-control" name="forWhat" value = <%=advertise.getForWhat()%>>
			<option>Rent</option>
			<option>Sale</option>
			<option>I still know what you did last summer</option>
		</select>
		</div>
		<br>
		
		<div class="input-group">
		 <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
		 <input  class="form-control" type="number" name="price" value = <%=advertise.getPrice()%>> 
		</div>
		<br>
		
		<div class="input-group">
		 <input type="file" name="mainPhoto" value = <%= advertise.getMainPhoto() %>>
		</div>
		<br>
		
		<div class="input-group">
		 <input type="file" value="Upload File" name="otherPhotos" multiple="true" value<%= advertise.getOtherPhotos()%>>
		</div>
	    <br>
	    <button  style = "margin-left: 20px;" value="EditAdv" name="EditAdv" class="btn btn-lg myAddAdvbtn">Save</button>
	     <br><br>
		 
       <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
      </div>
	</form>
	 </div>
  </div>
</div>
	<script>
		var clicked = true;
		function viewContact() {
			if (clicked == true) {
				document.getElementById('contactInfo').style.display = "block";
			} else {
				document.getElementById('contactInfo').style.display = "none";
			}
			clicked = !clicked;
		}
		
		 $(document).ready(function(){
		
					
			
		if(<%= request.getSession().getAttribute("curUser") != null%>)
		  {
			$('#commentOwner').removeAttr('disabled');
		  }
		else
			$('#commentOwner').attr('disabled', 'disabled');
		});
		
		
		
	</script>

</body>
</html>