package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Models.Advertise;
import Models.Normal;

@WebServlet("/AdvertiseController")
public class AdvertiseController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Advertise> allAdvertises = new ArrayList<Advertise>();

	public AdvertiseController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Advertise newAdvertise = new Advertise();
		newAdvertise.setTitle(request.getParameter("title"));
		newAdvertise.setDescription(request.getParameter("description"));
		newAdvertise.setType(request.getParameter("type"));
		newAdvertise.setStatus(request.getParameter("status"));
		newAdvertise.setForWhat(request.getParameter("forWhat"));  
		newAdvertise.setNumOfFloors(Integer.parseInt(request.getParameter("floors")));
		newAdvertise.setSize(Integer.parseInt(request.getParameter("size")));
		newAdvertise.setAddress(request.getParameter("address"));
		
		// get photos:
		HttpSession session = request.getSession();
		Normal user = (Normal) session.getAttribute("curUser");
		
		newAdvertise.setOwner(user);
		user.addAdvertise(newAdvertise);
		addAdvertise(newAdvertise);
		
		session.setAttribute("curAd", newAdvertise);
		response.sendRedirect("showAdvertise.jsp");
		
	}

	public void addAdvertise(Advertise advertise){
		allAdvertises.add(advertise);
	}
}
