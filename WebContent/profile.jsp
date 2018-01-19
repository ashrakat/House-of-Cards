<%@page import="DB.SelectFromDB"%>
<%@page import="jdk.nashorn.internal.runtime.UserAccessorProperty"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Models.Normal"%>
<%@ page import="Models.Admin"%>
<%@ page import="Models.Advertise"%>
<%@ page import="Models.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.io.*"%>
<%@ page import="java.lang.*"%>
<jsp:include page="navbar.jsp"></jsp:include>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="bootstrap_slate.css">
<link rel="stylesheet" href="style.css">
<script src="jquery-2.1.4.min.js"></script>
<script src="bootstrap.min.js"></script>

<title>HouseOfHouse</title>
<%
	if (request.getSession().getAttribute("error") != null){%>
	<script type="text/javascript">
			alert( "Error: Email is already Used, The information can't be updated!");
	</script>
<%
request.getSession().removeAttribute("error");
}%>

</head>




<body>

    <%
	  Normal user = new Normal();
      if(session.getAttribute("curUser") != null){  
		    user = (Normal)request.getSession().getAttribute("curUser");
      }
      
	  String path = user.getUserName() +".jpg";
		
	  if (request.getParameter("username") != null){
	    String username = request.getParameter("username");
	    user = (Normal)SelectFromDB.getCertainUserInfo(username);
		 path = user.getUserName() +".jpg";
	  }
	%>
	
	<div class = "container">
	<p class="title" style = "font-size:28px;"> <%=user.getUserName()%> </p>
     <div class = "row">
      <div class = "col-md-4">
       <div class = "thumbnail">
      <br>
       <img class ="MidImg" src="<%=path%>"  
		   alt="<%=user.getPhone()%>"> 
	   <br>
	    <p class="title" style="font-size: 24px;">Personal Info:</p>
      <ul>
        <li class="singleShow"><span class="declareAttr">Name:</span><%=user.getName()%></li>
		<br><br>
	    <li class="singleShow"><span class="declareAttr"> UserName:</span><%=user.getUserName()%></li>
		<br><br>
		<li class="singleShow"><span class="declareAttr">Email: </span><%=user.getEmail()%></li>
		<br><br>
		<li class="singleShow"><span class="declareAttr">Phone: </span><%=user.getPhone()%></li>
		
      </ul>
	   <button type="button" class="btn addAdv" data-toggle="modal"  style = "margin-left:55px;" data-target="#editProf">edit profile</button>
     </div>
      <div class = "thumbnail">
       <p class="title" style="font-size: 24px;">Preferences:</p>
      <ul>
       <%
       System.out.println("my Preferences = " + user.getPreferences().size());
       for(int i = 0 ; i < user.getPreferences().size();i++){
    	   if (!user.getPreferences().get(i).isDelete()){
       %>
        <li class="singleShow"><span class="declareAttr">Type:</span>
        <%=  user.getPreferences().get(i).getType() %>
        </li>
		<br>
		<li class="singleShow"><span class="declareAttr">Status:</span>
        <%=  user.getPreferences().get(i).getStatuse() %>
        </li>
		<br>
		<li class="singleShow"><span class="declareAttr">Size:</span>
        from: <%=  user.getPreferences().get(i).getSize().min %> to: <%=  user.getPreferences().get(i).getSize().max %>
        </li>
		<br>
		<li class="singleShow"><span class="declareAttr">Price:</span>
        from: <%=  user.getPreferences().get(i).getPrice().min %> to: <%=  user.getPreferences().get(i).getPrice().max %>
        </li>
		<br>
				
	    <%
         }
    	}
	    %>
      </ul>
      <br>
      <button class = "btn addAdv" id = "AddPrefBtn" style = "margin-left:55px;" onClick = "viewPrefForm()"> Add preference </button>
      <div = id = "AddPref">
       <form action = "UserController" method = "POST">
          <div class="form-group">
            <label for="pref_type">Type:</label>
            <input type="text" class="form-control" id="Pref_type"  name="type"/>
        </div>
        
        
        <div class="form-group"> <label for="pref_status">Status:</label>
        <select class="form-control" id="pref_status" name="status">
			<option>Finished</option>
			<option>Half Finished</option>
			<option>I know what you did last summer</option>
			</select>
		</div>
	
		
         <div class="form-group">
         <label for="pref_size">Size:</label>
         <input type="range" class="form-control" id="pref_size" name = "maxSize" min=0 max =10000 onchange="updateValue(this.value);">
         <input type = "text" id = "rangeValue" value = "" class = "range_value" name = "maxSize">
        </div>
        
         <div class="form-group">
         <label for="pref_price">Price:</label>
         <input type="range" class="form-control" id="pref_price" name = "maxPrice" min = 0 max = 1000000000 onchange="updateValue2(this.value);">
         <input type = "text" id = "rangeValue2" value = "" class = "range_value" name = "maxSize">
        
        </div>
        
        <center><button class="btn btn-lg myAddAdvbtn" name = "AddPref" style = "margin-right:20px;" id ="save">save</button></center>
        </form> 
      <div class="modal-footer">
        <button class="btn btn-warning" id = "cancel">cancel</button>
      </div>
     </div>  
    
    </div> 
    </div>
     
       <div class = "col-md-8 bkg">
      
        <p class="title" style="font-size: 24px;">My Advertises:</p>
        <%
         ArrayList<Advertise> myAdv = SelectFromDB.allUserAdvertises(user);
         path ="";
         for(int i = 0 ; i < myAdv.size();i++){
        	 if(myAdv.get(i).isClose()) continue;
        	 path = myAdv.get(i).getId() + ".jpg";
         
         %>
          <div class = "col-md-4 col-sm-12" id = <%=myAdv.get(i).getId()%>>
           <div class = "thumbnail">
              <a href=<%= "\"showAdvertise.jsp?id=" + myAdv.get(i).getId() + "\"" %>>
           
             <br>
             <!--  function ready in selectDB -->
            <img   src="<%=path%>" alt="" class="MidImg">
            <b> <p class = "title"> <%= myAdv.get(i).getTitle() %></p></b>
            <ul>
            <li> Price: <%= myAdv.get(i).getPrice() %>$</li>
            <li> Rate: <%= myAdv.get(i).getRate()%></li>
            </ul>
            </a>
            <center>
             <button class="btn" id="contactInfoButton" onclick="closeAdvertise('<%=myAdv.get(i).getId()%>')">Close</button>  
            </center>            
           </div>
           </div>
         <% } %>
    </div>
    </div>
    </div>
     
     <!-- Model for Edit prof form  -->
 <div id="editProf" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <center><h4 class="modal-title title" style = "font-size: 24px;">Edit profile </h4></center>
      </div>
      <div class="modal-body">
         <form action="UserController" method="POST" enctype="multipart/form-data">
	    <div class="input-group">
			<span class="input-group-addon input-group-sm"><i class = "fa fa-user fa-lg"></i></span>
          <input type="text" class="form-control" name = "name" value = <%= user.getName() %>>
        </div>
	  	 <br>   
	   
		 <div class="input-group">
			<span class="input-group-addon"><i class = "fa fa-mobile fa-lg"></i></span>
          <input type="number" class="form-control" name = "phone" value = <%= user.getPhone() %>>
        </div>
         <br>  
	   
		 <div class="input-group">
			<span class="input-group-addon"><i class = "fa fa-envelope-o fa-lg"></i></span>
          <input type="email" class="form-control" name = "email" value = <%= user.getEmail() %>>
        </div>
         <br>  
	  		   
	   <div class="input-group">
			<span class="input-group-addon"><i>&#128273;</i></span>
          <input type="password" class="form-control" name = "password" value = <%=user.getPassword() %>>
        </div>
        <br>
       
       <div class="input-group">
       	 <span class="input-group-addon"><i class = "fa fa-file-image-o"></i></span>
		 <input type="file" value="Upload File" name="pic" value = <%=path %>>
		</div>
	    <br>
	    
		<button value="EditProf" name="EditProf" class="btn btn-lg myAddAdvbtn">Save</button>
		 
	  </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
     
     <%
     if(request.getSession().getAttribute("curUser") != null){
      if (((User) request.getSession().getAttribute("curUser")).getType().equals("Admin")){
     %>
       <div class = "row"> 
        <div id = "AddforUser" style = "float:right;"> 
         <a href = "#"><button class = "btn addAdv">Change Password </button></a>
       </div>
      </div>
     <%
      }
     }
    %>
    
    <script>
    $(document).ready(function(){
    	 $("#AddPref").slideUp();
		 if(<%=request.getParameter("username") != null %>){
			 $('#AddPrefBtn').hide();
		     $('#EditProf').hide();
		 }
    });
    
    
    
    function viewPrefForm(){
    	console.log("here we go");
     $("#AddPref").slideDown();	
    }
    $("#save").click(function(){
    	$("#AddPref").slideUp();	
    });
    $("#cancel").click(function(){
    	$("#AddPref").slideUp();	
    });
    function updateValue(val) {
        document.getElementById('rangeValue').value=val; 
      }
    function updateValue2(val) {
        document.getElementById('rangeValue2').value=val; 
      }
        
     function closeAdvertise(id){
    	
    	$.get("UserController", {"id": id}, function(result){
    		alert("Succesfuly Closed");
    		$("#"+id).slideUp();
        });	
    }
    </script>
     
     

</body>
</html>