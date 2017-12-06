package Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Models.House;
import Models.Normal;
import Models.User;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public boolean valid(User user) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/IA?" + "user=root&password=noor92&characterEncoding=utf8");

			Statement stmt = conn.createStatement();
			String query = "select * from users where username = '" + user.getName() + "' ;";
			ResultSet rs = stmt.executeQuery(query);
			rs.last();
			int size = rs.getRow();
			if (size == 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addUser(HttpServletRequest request, HttpServletResponse response) {
		/// List of Login Users
		// User user = (User) request.getServletContext().getAttribute("Users");

		// add to database
		/// DataBase.addUser()

		// add to Session $ " library Of HttpSession"
		HttpSession session = request.getSession(true);
		Normal user = (Normal) session.getAttribute("CurrentUser");

		/***
		 * String Name = request.getParameter("Name"); String pass =
		 * request.getParameter("Password"); session.setAttribute("UName",
		 * Name); session.setAttribute("Password", pass);
		 */

		/// Check validation & add it in cookies

		if (valid(user)) {
			try {
				response.sendRedirect("profile.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/** save session id in [] of cookies ***/
			Cookie MyCurrentSession = new Cookie("MyCurrentSession", session.getId());
			MyCurrentSession.setMaxAge(5 * 60);
			response.addCookie(MyCurrentSession);
		}

		try {
			response.sendRedirect("SignUp.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addHouse(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		Normal user = (Normal) session.getAttribute("CurrentUser");
		House house = (House) session.getAttribute("NewHouse");

		user.addHouse(house);
		/** show all of his House **/
		try {
			response.sendRedirect("showproperties.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(true);
		session.removeAttribute("CurrentUser");
		
		try {
			response.sendRedirect("home.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public void showOwnHouses(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(true);
		Normal user = (Normal) session.getAttribute("CurrentUser");
		
		session.setAttribute("ListOfHouse", user.getListOfHouses());
		
		try {
			response.sendRedirect("showProperties.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void showAllNotification(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(true);
		Normal user = (Normal) session.getAttribute("CurrentUser");
		
		session.setAttribute("ListOfNotifications", user.getListOfNotifications());
		
		try {
			response.sendRedirect("allNotifications.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void addPreferences (HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession(true);
		Normal user = (Normal) session.getAttribute("CurrentUser");
		String preference =  (String) session.getAttribute("NewPreference");

		user.addPreferences(preference);
		
		try {
			response.sendRedirect("profile.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void showAllPreferences(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession(true);
		Normal user = (Normal) session.getAttribute("CurrentUser");
		
		session.setAttribute("ListOfPreferences", user.getPreferences());
		
		try {
			response.sendRedirect("allPreferences.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}
