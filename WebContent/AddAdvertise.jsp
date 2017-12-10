<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	var markers = [];

	function initialize() {
		geocoder = new google.maps.Geocoder();
		var mapOptions = {
			zoom : 8,
			center : new google.maps.LatLng(29.966339, 31.2541814)
		}
		map = new google.maps.Map(document.getElementById('map'), mapOptions);
	}

	function clearMarkers() {
		for (i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
	}

	function codeAddress() {
		clearMarkers();
		var address = document.getElementById('address').value;
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
				markers.push(curMarker);
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

	<h3>Adding new Advertise!</h3>
	<br><br>

	<form action="AdvertiseController" method="post">

		Advertise Title: <input type="text" name="title" > <br> <br>
		Description: <input type="text" name="description" > <br> <br>
		Type: <input type="text" name="type" > <br> <br>
		Status: <select name="status">
			<option>Finished</option>
			<option>Half Finished</option>
			<option>I know what you did last summer</option>
			</select> <br> <br>
		For What: <select name="forWhat" >
			<option>Rent</option>
			<option>Sale</option>
			<option>I still know what you did last summer</option>
			</select> <br> <br>
		Number Of Floors: <input type="text" name="floors" > <br> <br>
		Size: <input type="text" name="size" > <br> <br>
		Advertise Main Photo: <input name="mainPhoto" type="file">
		<input type="submit" value="Upload File" > <br> <br>
		Other Photos: <input name="otherPhotos" type="file" multiple>
		<input type="submit" value="Upload Files" > <br> <br>
		<div id="map" style="width: 320px; height: 480px;"></div>
		Address: <input id="address" type="text" name="address" > 
		<input type="button" value="Find" onclick="codeAddress()" > <br> <br>
		<input type="submit" value="Add Advertise" >

	</form>
</body>
</html>