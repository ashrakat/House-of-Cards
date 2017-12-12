package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.InsertIntoDB;
import DB.SelectFromDB;
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
		newAdvertise.setPrice(Integer.parseInt(request.getParameter("price")));
		

		// get photos:
		HttpSession session = request.getSession();
		Normal user = (Normal) session.getAttribute("curUser");
		
		newAdvertise.setOwner(user);
		user.addAdvertise(newAdvertise);
		addAdvertise(newAdvertise);
		

		//photos:
		//String userName = "salma"; //TODO get from session
		//TODO add mainController to application scope
		//MainController mainController = (MainController) request.getServletContext().getAttribute("mainController");
		//newAdvertise.setOwner((Normal)DB.getCertainUserInfo(userName));
		//newAdvertise.getOwner().addAdvertise(newAdvertise);
		//mainController.addAdvertise(newAdvertise);
		
		int id = InsertIntoDB.addAdvertise(newAdvertise);
		System.out.println(id);
		response.sendRedirect("\\House_of_Cards\\showAdvertise.jsp?id="+id);
		
	}

	public void addAdvertise(Advertise advertise){
		allAdvertises.add(advertise);
	}
}
