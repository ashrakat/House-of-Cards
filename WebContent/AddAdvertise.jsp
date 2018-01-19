<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<jsp:include page="navbar.jsp"></jsp:include>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	if (request.getSession().getAttribute("Photo") != null){%>
	<script type="text/javascript">
			alert( "Error: your photos size is greater than max size");
	</script>
<%
request.getSession().removeAttribute("Photo");
}%>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&key=AIzaSyDKqm6FfQVWma0atI6iRmLd89IKFkUyDuY"></script>
<link rel="stylesheet" href="bootstrap_slate.css">
<link rel="stylesheet" href="style.css">
<script src="jquery-2.1.4.min.js"></script>
<title>Add Advertise</title>
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

 <div class = "container">

    <div class="modal-dialog" id = "AddADV">	
    <div class="modal-content">
      <div class="modal-header">
        <div class="modal-title">
          <center class = "register">
           <img style = "max-width:500%; max-height:200px;margin-top:10px;"src="house1.png"><br>
           Adding new Advertise!
          </center>
         </div>
		</div>
	 <br>
	 <center>
    <div class ="row">
     <div class = "col-md-5 col-md-offset-3">
	<form action="AdvertiseController" method="POST" enctype="multipart/form-data">

       <div class="input-group">
       <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
	    <input class="form-control" type="text" name="title" placeholder = "Title" required> 
	   </div>
	   <br>
	   
	   <div class="input-group">
	    <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
	    <input type="text" class="form-control" name="description" placeholder = "Description" required>
	   </div> 
	   <br> 
	   
	   <div class="input-group">
	    <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
	    <input type="text" class="form-control"  name="type" placeholder = "Type" required> 
	   </div>
	   <br>
	    
	   <div class="input-group">
	    <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
	    <select class="form-control" name="status" required>
			<option>Finished</option>
			<option>Half Finished</option>
			<option>I know what you did last summer</option>
			</select>
		</div>
		<br>
		
		<div class="input-group">
		 <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
	     <select class="form-control" name="forWhat" >
			<option>Rent</option>
			<option>Sale</option>
			<option>I still know what you did last summer</option>
		</select>
		</div>
		<br>
		
		<div class="input-group">
		 <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
		 <input  class="form-control"  type="number"  name="floors" placeholder = "# of floors" min=1 required>
		</div>
		<br>
		
		<div class="input-group">
		 <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
		 <input  class="form-control" type="number" name="size" placeholder = "Size of the house" required> 
		</div>
		<br>
		
		<div class="input-group">
		 <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
		 <input  class="form-control" type="number" name="price" placeholder = "Price of the house" required> 
		</div>
		<br>
		
		<div>Max size = 45KB</div>
		<div class="input-group">
		 <input type="file" name="mainPhoto" >
		</div>
		<br>
		
		<div>Max size = 45KB for each photo </div>
		<div class="input-group">
		 <input type="file" value="Upload File" name="otherPhotos" multiple="true" >
		</div>
	    <br>
	
		
		<div class="input-group"> 
		 <span class="input-group-addon"><i class = "fa fa-home fa-lg"></i></span>
	     <input class="form-control"  id="address" type="text" name="address" placeholder = "Address" required> 
	     </div>
	     <br>
	    <input type="button" value="Find" onclick="codeAddress()" > <br> <br>
		 
	     <div  id="map" style="width: 320px; height: 480px; margin-left:-30px;"></div>
	     <br><br>
	     <button class = "btn btn-lg myAddAdvbtn" type="submit" value="Add Advertise" >Add Advertise</button>
	     <br><br>
		
	</form>
	</div>
	</div>
 </center>
 </div>
 </div>
 </div>
 
 <script>
       
       $(document).ready(function(){
         $("#AddADV").fadeIn(1200);   
       });
   </script>
 
</body>
</html>