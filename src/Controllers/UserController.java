package Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DB.*;
import Models.*;
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
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("newpass") != null)
			changePassword(request, response);
		else
			closeUserAdvertise(request, response);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("SignUp") != null) {
			signUp(request, response);
		}
		if (request.getParameter("EditProf") != null) {
			editProfile(request, response);
		}
		if (request.getParameter("AddPref") != null) {
			addPreference(request, response);
		}
		if (request.getParameter("suggest") != null) {
			checkUserName(request, response);
		}
		if (request.getParameter("mail") != null) {
			checkMail(request, response);
		}
	}

	public void checkUserName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			String username = request.getParameter("suggest");
			if (DB.SelectFromDB.checkUserName(username))
				out.print("Corrent");
			else
				out.print(" Used Username");
		}

	}

	public void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String pass = request.getParameter("newpass");
		UpdateInDB.userPassword(username, pass);

	}

	private void closeUserAdvertise(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in close in userC");
		String id = request.getParameter("id");
		UpdateInDB.cloaseAdvertise(Integer.parseInt(id));
	}

	public void checkMail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			String email = request.getParameter("mail");
			if (DB.SelectFromDB.checkMail(email))
				out.print("new Mail");
			else
				out.print("Used Mail");
		}

	}

	public void addPreference(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println("in add preference");
		Preference preference = new Preference();
		preference.setType(request.getParameter("type"));
		preference.setStatuse(request.getParameter("status"));
		int minSize = 0; // Integer.parseInt(request.getParameter("minSize"));
		int maxSize = Integer.parseInt(request.getParameter("maxSize"));
		preference.setSize(new PreferenceRange(minSize, maxSize));
		int minPrice = 0; // Integer.parseInt(request.getParameter("minPrice"));
		int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
		preference.setPrice(new PreferenceRange(minPrice, maxPrice));

		Normal user = (Normal) request.getSession().getAttribute("curUser");
		user.addPreference(preference);
		InsertIntoDB.addPreference(preference, user);
		response.sendRedirect("profile.jsp");

	}

	public void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String username = request.getParameter("userName");

		Part filePart = request.getPart("pic"); // Retrieves <input type="file" name="file">
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
			if (InsertIntoDB.addUser(user))
				response.sendRedirect("logIn.jsp");
			else {
				String message = "Error: your photo size is greater than max size";
				request.getSession().setAttribute("Photo", message);
				response.sendRedirect("signUp.jsp");
			}
		} else {
			/*
			 * out.print("<script>");
			 * out.print("alert('Username Or Email is already Used') ;");
			 * out.print("</script> ");
			 */
			String message = "Error: Username Or Email is already Used";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("signUp.jsp");
		}
	}

	public void showAllPreferences(HttpServletRequest request, HttpServletResponse response) {

		Normal user = (Normal) request.getSession().getAttribute("currName");
		request.getSession().setAttribute("listOfPreferences", user.getPreferences());
	}

	public void editProfile(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		User user = (Normal) request.getSession().getAttribute("curUser");

		
		String mail = request.getParameter("email");

		if (SelectFromDB.checkMail(mail)) {
			user.setEmail(request.getParameter("email"));
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			user.setPhone(request.getParameter("phone"));
			user.setType("Normal");
			Part filePart = request.getPart("pic");
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			InputStream fileContent = filePart.getInputStream();

			user.setPic(fileContent);
			UpdateInDB.UpdateUser(user);
			request.getSession().setAttribute("curUser", user);
		} 
		else
			request.getSession().setAttribute("error", true);

		response.sendRedirect("profile.jsp");
	}

}
