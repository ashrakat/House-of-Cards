package Controllers;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DB.SelectFromDB;
import Models.Normal;
import Models.User;
import DB.InsertIntoDB;

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

	// check for username and mail for sign up to not be repeated

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// All about login process
		String username = request.getParameter("userName");
		String pass = request.getParameter("password");
		HashMap<String, HttpSession> sessionsManager = (HashMap<String, HttpSession>) request.getServletContext()
				.getAttribute("sessionManager");

		if (request.getParameter("logout") != null) // logout is active
		{
			if (sessionsManager == null)
				request.getSession().invalidate();
			else {
				sessionsManager.remove(request.getSession().getId());
				request.getServletContext().setAttribute("sessionManager", sessionsManager);
				request.getSession().setMaxInactiveInterval(0);
				request.getSession().invalidate();
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
			response.sendRedirect("Home.jsp");
		} else { // log in is active

			if (SelectFromDB.checkUserPass(username, pass)) {
				HttpSession logInsession = request.getSession(true);
				logInsession.setAttribute("userName", username);
				logInsession.setMaxInactiveInterval(5 * 60);
				sessionsManager.put(logInsession.getId(), logInsession);
				request.getServletContext().setAttribute("sessionManager", sessionsManager);
				Cookie MyCurrentSession = new Cookie("sessionId", logInsession.getId());
				MyCurrentSession.setMaxAge(5 * 60);
				response.addCookie(MyCurrentSession);
				response.sendRedirect("Home.jsp");
			}

			else
				response.sendRedirect("logIn.jsp");

		}
	}

	// Sign up process
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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

	protected void dologout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// As in log out we only remove cookies and sessions we don't remove from
		// database it's not an deactivate
		HashMap<String, HttpSession> search = (HashMap<String, HttpSession>) request.getServletContext()
				.getAttribute("sessionManager");
		search.remove(request.getSession().getId());
		request.getServletContext().setAttribute("sessionManager", search);
		request.getSession().setMaxInactiveInterval(0);
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		response.sendRedirect("Home.jsp");
	}

}
