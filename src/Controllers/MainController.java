package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.SelectFromDB;
import Models.Advertise;


@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public MainController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("showHouses") != null){
			showAllHouses(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void showAllHouses(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ArrayList<Advertise> houses = SelectFromDB.allAdvertises();
		if(houses == null)
			 houses = new ArrayList<Advertise>();
		request.setAttribute("houses", houses);
		RequestDispatcher rd= request.getRequestDispatcher("Home.jsp");
		rd.forward(request,response);
		
	}

}
