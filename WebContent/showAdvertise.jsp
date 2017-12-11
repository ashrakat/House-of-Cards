<%@page import="DB.SelectFromDB"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "Models.Advertise" %>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Add Advertise</title>
</head>

<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&key=AIzaSyDKqm6FfQVWma0atI6iRmLd89IKFkUyDuY">
	
</script>

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
				alert('Geocode was not successful for the following reason: ' + status);
			}
		});
	}
</script>


<body onload="initialize()">
		<%
		  System.out.println(request.getParameter("id"));
		   int id = Integer.parseInt(request.getParameter("id"));
			
		   Advertise advertise = SelectFromDB.getCertainAdvertiseInfo(id);
		%>
		Advertise Title: <%= advertise.getTitle() %> <br> <br>
		Description: <%= advertise.getDescription() %> <br> <br>
		Type: <%= advertise.getType() %> <br> <br>
		Status: <%= advertise.getStatus() %> <br> <br>
		For What: <%= advertise.getForWhat() %> <br> <br>
		Number Of Floors: <%= advertise.getNumOfFloors() %> <br> <br>
		Size: <%= advertise.getSize() %> <br> <br>
		
		<% //show photos %>
		
		Address: <%= advertise.getAddress() %> <br> <br>
		<div id="map" style="width: 320px; height: 480px;"></div> <br>
		<input type="button" value="show address on map" onclick="codeAddress('<%= advertise.getAddress() %>')">
		<br> <br>
		
	
		<%//show user contact info %>

</body>
</html>