package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.SelectFromDB;
import Models.House;

@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("showHouses") != null)
		{
			showAllHouses(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void showAllHouses(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<House> houses = SelectFromDB.allHouses();
		request.setAttribute("houses", houses);
		System.out.println("Here we go " + houses.size());
		response.sendRedirect("Home.jsp");
	}

}
