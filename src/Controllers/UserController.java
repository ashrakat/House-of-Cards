package Controllers;
import Models.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.InsertIntoDB;
import DB.SelectFromDB;

import java.io.*;


/**
 * Servlet implementation class UserControoler
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		signUp(request , response);
		
	}
	
	public boolean valid(User user) {
		if (SelectFromDB.getCertainUserInfo(user.getUserName()) == null)
			return true ;
	
		return false ; 
	}

	public void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String username = request.getParameter("userName");
		
		if (SelectFromDB.CheckemailandUser(email, username)) {
			User user = new Normal () ;
			user.setEmail(email);
			user.setName(username);
			user.setPassword(pass);
			user.setPhone(phone);
			user.setUserName(username);
			user.setType("Normal");
			InsertIntoDB.addUser(user);

			response.sendRedirect("logIn.jsp");
		     } 
		else {
			response.sendRedirect("logInUp.jsp");
		}
	}


	public void showAllNotification(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Normal user = (Normal)  request.getSession().getAttribute("currName");
		request.getSession().setAttribute("listOfNotifications", user.getListOfNotifications());
		response.sendRedirect("allNotifications.jsp");
	}
	
	public void addPreferences (HttpServletRequest request, HttpServletResponse response){
		
		Normal user = (Normal)  request.getSession().getAttribute("currName");
	}
	
	public void showAllPreferences(HttpServletRequest request, HttpServletResponse response){
		
		Normal user = (Normal)  request.getSession().getAttribute("currName");
		request.getSession().setAttribute("listOfPreferences", user.getPreferences());
	}
	

}
