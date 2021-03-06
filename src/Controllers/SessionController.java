package Controllers;

import DB.*;
import Models.*;

import java.util.HashMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class SessionController
 */
@WebServlet("/SessionController")
public class SessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SessionController() {
	}

	// check for username and mail for sign up to not be repeated
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// All about login process
		if (request.getParameter("logout") != null) // logout is active
		{
			logout(request, response);
		} else { // log in is active
			logIn(request, response);
		}
	}

	// Sign up process
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void logIn(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String username = request.getParameter("userName");
		String pass = request.getParameter("password");
		
		HashMap<String, HttpSession> sessionsManager = (HashMap<String, HttpSession>) request.getServletContext()
				.getAttribute("sessionManager");
		
		if (SelectFromDB.checkUserPass(username, pass)) {
			HttpSession logInsession = request.getSession(true);
			User user = SelectFromDB.getCertainUserInfo(username);
			
			if(user.getType().equals("Normal")) {
				Normal normalUser =(Normal) user; 
				normalUser.setPreferences(DB.SelectFromDB.loadAllUserPreferences(user.getUserName()));
				logInsession.setAttribute("curUser", normalUser);
			  }
			else
			logInsession.setAttribute("curUser", user);
			logInsession.setMaxInactiveInterval(5 * 60);
			sessionsManager.put(logInsession.getId(), logInsession);
			request.getServletContext().setAttribute("sessionManager", sessionsManager);
			Cookie MyCurrentSession = new Cookie("sessionId", logInsession.getId());
			MyCurrentSession.setMaxAge(5 * 60);
			response.addCookie(MyCurrentSession);
			response.sendRedirect("Home.jsp");
		}

		else {
			String message = "Error: Username Or Email is already Used";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("logIn.jsp");}

	}

	protected void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		HashMap<String, HttpSession> search = (HashMap<String, HttpSession>) request.getServletContext()
				.getAttribute("sessionManager");
		
		if (search == null)
			request.getSession().invalidate();
		else {
			search.remove(request.getSession().getId());
			request.getServletContext().setAttribute("sessionManager", search);
			request.getSession().setMaxInactiveInterval(0);
			request.getSession().invalidate();
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		response.sendRedirect("Home.jsp");
	}
}
