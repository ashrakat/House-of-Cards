package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.*;
import Extra.Fillteration;
import Models.*;
import Models.Notification.notificationType;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("showHouses") != null)
			showAllHouses(request, response);
		
		if(request.getParameter("closeID") != null)
			closeUserAdvertise (request, response);
		
		if(request.getParameter("suspendID") != null)
			suspendUserAdvertise(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("suggest")!= null)
			showAllActiveAdvertises (request, response);
		if(request.getParameter("filteration")!= null) {
			filterOptions(request,response);
		 }
	}
	
	private void closeUserAdvertise(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Anaaaaaaaaaaaaa hna ");
		String id = request.getParameter("closeID");
		UpdateInDB. cloaseAdvertise(Integer.parseInt(id));	
	}
	
	private void suspendUserAdvertise(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("weeeeeeeeeeeeeeeeeeee");
		String id = request.getParameter("suspendID");
		int i= Integer.parseInt(request.getParameter("index"));
		System.out.println("suspendID : " + id + "index : " + i );
		ArrayList <Advertise> houses = new ArrayList <Advertise>(); 
		UpdateInDB.suspendAdvertise(Integer.parseInt(id), !houses.get(i).isSusbinded());
	}
	
	protected void showAllHouses(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
		
		ArrayList<Advertise> houses = SelectFromDB.allAdvertises();
		if(houses == null)
			 houses = new ArrayList<Advertise>();
		request.getSession().setAttribute("houses", houses);
		RequestDispatcher rd= request.getRequestDispatcher("Home.jsp");
		rd.forward(request,response);
		
	}
	
	public void sendCommentNotification(Advertise advertise, String URL, Normal curUser){
		
		Notification notification = new Notification();
		notification.setLink(URL);
		notification.setBody(notificationType.Comment);
		
		//notify owner if comment is written by someone else
		if(advertise.getOwner().getUserName() != curUser.getUserName()){
			advertise.getOwner().addNotification(notification);
			InsertIntoDB.addNotification(advertise.getOwner(), notification);
			System.out.println("notification send to owner: " + advertise.getOwner().getUserName() );
		}
		
		//notify everyone who made a comment before that there is a new one if not advertise owner or current user
		ArrayList<Comment> comments = advertise.getComments() ;
		for(int i=0 ; i<comments.size() ; i++){
			if(comments.get(i).getOwner().getUserName() != advertise.getOwner().getUserName() &&
				comments.get(i).getOwner().getUserName() != curUser.getUserName()){
				((Normal) comments.get(i).getOwner()).addNotification(notification);
				InsertIntoDB.addNotification(comments.get(i).getOwner(), notification);
			}		
		}
	}
	
	public void sendPreferenceNotification(Advertise advertise, String URL) throws IOException{
		
		Notification notification = new Notification();
		notification.setLink(URL);
		notification.setBody(notificationType.Advertise);
		ArrayList<Normal> allUsers = SelectFromDB.allUsers();
		
		for(int i=0 ; i<allUsers.size() ; i++){
			ArrayList<Preference> userPreferences = allUsers.get(i).getPreferences();
			for(int j=0 ; j<userPreferences.size() ;j++){
				if(advertiseMeetsPreference(advertise, userPreferences.get(j))){
					allUsers.get(i).addNotification(notification);
					InsertIntoDB.addNotification(allUsers.get(i), notification);
					break;
				}
			}
		}
	}

	public boolean advertiseMeetsPreference(Advertise advertise, Preference preference) {
	
		if(preference.getType().equals(advertise.getType()) && preference.getStatuse().equals(advertise.getStatus()) &&
			advertise.getSize() >= preference.getPrice().min && advertise.getSize() <= preference.getSize().max &&
			advertise.getPrice() >= preference.getPrice().min && advertise.getPrice() <= preference.getPrice().max){
				return true;
			}
		
		return false;
	}

	public void showAllActiveAdvertises(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		 response.setContentType("text/html;charset=UTF-8");
	       

		 try (PrintWriter out = response.getWriter()) {
	         int id = Integer.parseInt(request.getParameter("suggest"));
	          String action = request.getParameter("event");
	          System.out.println("Id  : " + id);
	          System.out.println("Action : " + action);
	          
	          ArrayList <Advertise> Advertises = (ArrayList<Advertise>) request.getSession().getAttribute("houses");
	          String path ="";
	          out.print("<div class='container' id='main'>");
	          out.print("<div class='container'>");
	          out.print("<div class='row'>");
	          
	          for(int i = 0 ; i <Advertises.size(); i++){  
	        	  path =Advertises.get(i).getId() + ".jpg";
	              String state = "" , start="" ,end="";
	             
	        	  if(Advertises .get(i).getId() == id) {
	            		if(action.equals("Suspend")) {
	            			Advertises.get(i).setSusbinded(!Advertises.get(i).isSusbinded());
	            			DB.UpdateInDB.suspendAdvertise(id ,Advertises.get(i).isSusbinded() );}
	            		else {
	            			Advertises.get(i).setClose(true);
	            			DB.UpdateInDB.cloaseAdvertise(id);
	            		}
	        	  }
	        	  if(Advertises.get(i).isSusbinded()) {
	            	  state="Unsuspend"; 
	            	  start="<mark>";
	            	  end="</mark>";
	            	  }
	              else 
	            	  state="Suspend";
	             if(!(Advertises.get(i).isClose()) ){
	            	out.print(
	            	"<a href='\'showAdvertise.jsp?id='" + Advertises.get(i).getId() + "'\''>" +
					   "<div class='col-md-3 col-sm-12'>" +
						  "	<div class='thumbnail'>" +  "<br> "+
							"<img src= '"+ path + "' alt='' class='MidImg'> <b>" +
								"<p class='title'> "+start+Advertises.get(i).getTitle()+end+ "</p> "+
							    "</b><ul> " + 
									"<li>ID:"+ Advertises.get(i).getId() +"</li>" +
									"<li>Price:" + Advertises.get(i).getPrice() +" $" + "</li> " +
									"<li>Rate:"+Advertises.get(i).getRate()+"</li>" +
									"<li>Suspend:"+state+"</li>" +
								"</ul>" +
					//			"<center><span><button class ='btn myAddAdvbtn btn-sm' value='Close_"+Advertises.get(i).getId()+"'>Close</button> " +
					//            "<button  class ='btn myAddAdvbtn btn-sm' value='Suspend_"+Advertises.get(i).getId()+"'>Suspend</button></span></center>" +
							"</div>"+
						"</div>"+
						"</a>" );
	   
	             }
	           }  
	          out.print("</div></div></div>");
	        }
	    
	}
  
	
	public void filterOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Fillteration filter = new Fillteration();
		  ArrayList<Advertise> houses = (ArrayList<Advertise>) request.getSession().getAttribute("houses");
		  int size = 1 , numOfFloors = 1;
		  double rate = 0.0 , price = 100000.0;
		  String type = "", Address = "";
		  if(request.getParameter("size") != null)
		    size = Integer.parseInt(request.getParameter("size"));
		  if(request.getParameter("#ofFloor") != null)
		    numOfFloors = Integer.parseInt(request.getParameter("#ofFloor"));
		  if(request.getParameter("price") != null)
		    price = Double.parseDouble(request.getParameter("price"));
		  if(request.getParameter("rate") != null)
		    rate = Double.parseDouble(request.getParameter("rate"));
		  if(request.getParameter("type") != null)
		    type = request.getParameter("type");
		  if(request.getParameter("Address") != null)
		    Address = request.getParameter("Address");
	
		  houses = filter.filterByAll(type, Address, rate, price, numOfFloors, size, houses);
		  
		  request.setAttribute("houses", houses);
		  RequestDispatcher rd= request.getRequestDispatcher("Home.jsp");
		  rd.forward(request,response);	
	}

}

