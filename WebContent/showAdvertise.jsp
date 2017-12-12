<%@page import="DB.SelectFromDB"%>
<%@page import="Controllers.*"%>
<%@page import="Models.*"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<jsp:include page="navbar.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="Models.Advertise"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Add Advertise</title>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="bootstrap_slate.css">
<link rel="stylesheet" href="style.css">
<script src="jquery-2.1.4.min.js"></script>
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
	%>
	<p class="singleShowTitle">
		<%=advertise.getTitle()%>
	</p>
	<br>
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
				int len = 2;
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
					<li class="singleShow"><span class="declareAttr"> Size:
					</span><%=advertise.getSize()%></li>
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
									<li><span class="declareUserAttr"> Name: </span><%=advertise.getOwner().getName()%></li>
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
					<div id="map" style="width: 320px; height: 450px;"></div>
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
					for (int i = 0; i < commentsSize; i++) {
				%>
				<ul>
					<li><span class="declareUserAttr"> <a
							href=<%="\"showAdvertise.jsp?id=" + advertise.getComments().get(i).getOwner().getUserName() + "\""%>">
								<%=advertise.getComments().get(i).getOwner().getUserName()%>
						</a>
					</span><%=advertise.getComments().get(i).getBody()%></li>
				</ul>
				<%
					}
				%>
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
	</script>


</body>
</html>