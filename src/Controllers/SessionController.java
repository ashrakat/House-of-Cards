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

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("userName");
		String pass = request.getParameter("password");

		if (request.getParameter("logout") != null) // logout is active
		{
			logout(request, response);
		} 
		else { // log in is active
            logIn(request , response , username , pass );
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	
	protected void logIn(HttpServletRequest request, HttpServletResponse response , String username , String pass) throws IOException {
		HashMap<String, HttpSession> sessionsManager = (HashMap<String, HttpSession>) request.getServletContext().getAttribute("sessionManager");
		if (SelectFromDB.checkUserPass(username, pass)) {
			HttpSession logInsession = request.getSession(true);
			User user = SelectFromDB.getCertainUserInfo(username); 
			logInsession.setAttribute("curUser", user);
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

	protected void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, HttpSession> search = (HashMap<String, HttpSession>) request.getServletContext().getAttribute("sessionManager");
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
<<<<<<< HEAD
}
=======
}
>>>>>>> e5d0e2c2cbd8370b1a7eaf0e66a6b3046ca57818
