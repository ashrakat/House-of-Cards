package Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Models.Advertise;

@WebServlet("/AdvertiseController")
public class AdvertiseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdvertiseController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		//photos:
		//String userName = "salma"; //TODO get from session
		//TODO add mainController to application scope
		//MainController mainController = (MainController) request.getServletContext().getAttribute("mainController");
		//newAdvertise.setOwner((Normal)DB.getCertainUserInfo(userName));
		//newAdvertise.getOwner().addAdvertise(newAdvertise);
		//mainController.addAdvertise(newAdvertise);
		
		HttpSession session = request.getSession();
		session.setAttribute("curAd", newAdvertise);
		response.sendRedirect("showAdvertise.jsp");
		
	}

}
