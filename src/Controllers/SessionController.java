package Controllers;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class SessionController
 */
@WebServlet("/SessionController")
public class SessionController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
		Connection Con = null;
	    Statement Stmt = null;
	    ResultSet RS = null;

	   public SessionController() throws SQLException {	
	     }
	   	
	   public void SqlConnection() throws SQLException {
		   try{
				 Class.forName("com.mysql.jdbc.Driver");
				}
				catch(ClassNotFoundException e){
					e.printStackTrace();
				}
		   String url = "jdbc:mysql://localhost:3306/accounts";
		   String user = "root";
		   String password = "shosho";
		   Con = (Connection) DriverManager.getConnection(url, user, password);
		   Stmt = Con.createStatement();
	   }
	   
	   // check for username and mail for sign up to not be repeated
	   public boolean checkUserNameAndMail( HttpServletRequest request, String email , String userName) {
		  HashMap<String , HttpSession> sessionsManager = (HashMap<String , HttpSession>) request.getServletContext().getAttribute("sessionManager");
		// iterate on map till you find the target email and password
		  for (Map.Entry<String, HttpSession> entry : sessionsManager.entrySet())
		  {
			  if(entry.getValue().getAttribute("session_UserName").equals(userName) ||entry.getValue().getAttribute("session_Email").equals(email)) 
        	    return false;   // found
          }
          return true;   // unique
		   
	   }
       
	   
	   public boolean checkmailAndpassword(String email , String password) throws SQLException {
		   // iterate on map till you find the matched mail and password
		   SqlConnection();  
		  RS =  Stmt.executeQuery( "SELECT * from User where mail =  '"+email+"' and password = '"+password+"'"); 
		  if(RS != null)
		  {
			 return true;  // in the system and matched 
		  }
		  else
		   return false;   // one of them is incorrect
 	   }
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// All about login process	
	 String email = request.getParameter("email");
	 String pass = request.getParameter("password");	
	 try {
		if(checkmailAndpassword(email, pass)) {
			response.sendRedirect("Home.jsp");
			 HttpSession logInsession = request.getSession(true);
			 logInsession.setAttribute("email", email);
		}
		   
		 else
			response.sendRedirect("logIn.jsp");
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String phone = request.getParameter("phoneNumber");
		String name = request.getParameter("name");
		String username = request.getParameter("userName");
        if(checkUserNameAndMail(request,email ,username)) {
		 HttpSession sessionn = request.getSession(true);
		 sessionn.setAttribute("session_Email", email);
	     sessionn.setAttribute("session_Pass", pass);
	     sessionn.setAttribute("session_UserName", username);
	     sessionn.setMaxInactiveInterval(60);
	     HashMap<String , HttpSession> sessionsManager = (HashMap<String , HttpSession>) request.getServletContext().getAttribute("sessionManager");
	     sessionsManager.put(sessionn.getId(), sessionn);
	   	 request.getServletContext().setAttribute("sessionManager", sessionsManager);
         Cookie MyCurrentSession = new Cookie ("sessionId",sessionn.getId());
         MyCurrentSession.setMaxAge(60);
         response.addCookie(MyCurrentSession);
         try {
			SqlConnection();
			Stmt.executeUpdate( "insert into User values ('"+sessionn.getId()+"', '"+email+"', '"+pass+"', '"+phone+"' , '"+name+"', '"+username+"')");
			 //RS = Stmt.executeQuery("SELECT * FROM users;");
         } catch (SQLException e) {
	    	e.printStackTrace();
		 }
		
         response.sendRedirect("logIn.jsp");
        }
        else {
         response.sendRedirect("longInUp.jsp");
        }
	}

}
