package Controllers;
import Models.Advertise;
import Models.User;
import Models.Normal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DB.*;

import java.io.*;
import java.nio.file.Paths;



/**
 * Servlet implementation class UserControoler
 */
@WebServlet("/UserController")
@MultipartConfig(maxFileSize = 1699999999)
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserControoler() {
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
		// TODO Auto-generated method stub
		signUp(request , response);
		}

	public boolean valid(User user) {
		if (SelectFromDB.getCertainUserInfo(user.getUserName()) == null)
			return true ;

		return false ;
	}

	public void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String username = request.getParameter("userName");

		Part filePart = request.getPart("pic"); // Retrieves <input type="file" name="file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		InputStream fileContent = filePart.getInputStream();

		if (SelectFromDB.CheckemailandUser(email, username)) {
			User user = new Normal();
			user.setEmail(email);
			user.setName(name);
			user.setPassword(pass);
			user.setPhone(phone);
			user.setUserName(username);
			user.setType("Normal");
			user.setPic(fileContent);
			InsertIntoDB.addUser(user);
			response.sendRedirect("logIn.jsp");
		     }
		else {
			response.sendRedirect("signUp.jsp");
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
