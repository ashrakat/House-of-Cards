package Controllers;

import java.io.*;
import java.util.*;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DB.*;
import Models.*;

/**
 * Servlet implementation class AdvertiseController
 */
@WebServlet("/AdvertiseController")
@MultipartConfig(maxFileSize = 1699999999)
public class AdvertiseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Advertise> allAdvertises = new ArrayList<Advertise>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdvertiseController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(request.getParameter("comment") != null)
			  addComment(request,response);
	    	else {
	    		addRate(request, response);
	    	}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		if(request.getParameter("EditAdv") != null) {
			editAdvertise(request, response);
		}
		else {
		 addNewAdvertise(request,response);
		}
	}
	
	@SuppressWarnings("deprecation")
	protected void addComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String body = request.getParameter("comment");
		Advertise advertise = SelectFromDB.getCertainAdvertiseInfo(id);
		Date dateobj = new Date();
		Comment comment = new Comment();
		comment.setBody(body);
		comment.setDate(dateobj);
		System.out.println("id " + id  );
		InsertIntoDB.addComment((User)request.getSession().getAttribute("curUser"), advertise, comment);
		
		Normal curUser = (Normal) request.getSession().getAttribute("curUser");
		MainController mainController = (MainController) request.getServletContext().getAttribute("mainController");
		String URL = (String) request.getSession().getAttribute("URL");
		mainController.sendCommentNotification(advertise, URL, curUser);
		
		response.sendRedirect("showAdvertise.jsp?id="+id+"");
	}
	
	private void addNewAdvertise(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Advertise newAdvertise = new Advertise();
		newAdvertise.setTitle(request.getParameter("title"));
		newAdvertise.setDescription(request.getParameter("description"));
		newAdvertise.setType(request.getParameter("type"));
		newAdvertise.setStatus(request.getParameter("status"));
		newAdvertise.setForWhat(request.getParameter("forWhat"));
		newAdvertise.setNumOfFloors(Integer.parseInt(request.getParameter("floors")));
		newAdvertise.setSize(Integer.parseInt(request.getParameter("size")));
		newAdvertise.setAddress(request.getParameter("address"));
		newAdvertise.setRate(0.0);
		newAdvertise.setPrice(Double.parseDouble(request.getParameter("price")));
		newAdvertise.setSusbinded(false);

		Part filePart1 = request.getPart("mainPhoto"); // Retrieves <input type="file" name="file">
		String fileName1 = Paths.get(filePart1.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		InputStream fileContent1 = filePart1.getInputStream();
		newAdvertise.setMainPhoto(fileContent1);

		List<Part> fileParts = request.getParts().stream().filter(part -> "otherPhotos".equals(part.getName()))
				.collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">

		for (Part filePart : fileParts) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			InputStream fileContent = filePart.getInputStream();
			newAdvertise.addPhoto(fileContent);
		}
		
		HttpSession session = request.getSession();
		Normal user = (Normal) session.getAttribute("curUser");

		newAdvertise.setOwner(user);
		user.addAdvertise(newAdvertise);
		
		int id = InsertIntoDB.addAdvertise(newAdvertise);
		if(id == -2) {
			String message = "Error: your photo size is greater than max size";
			request.getSession().setAttribute("Photo", message);
			response.sendRedirect("AddAdvertise.jsp");
		}
		else {
			newAdvertise.setId(id);
			addAdvertise(newAdvertise);
			session.setAttribute("curAd", newAdvertise);
			
			MainController mainController = (MainController) request.getServletContext().getAttribute("mainController");
			String URL = "http://localhost:8080/HouseOfCards/showAdvertise.jsp?id=" + newAdvertise.getId();
			mainController.sendPreferenceNotification(newAdvertise, URL);
		
			response.sendRedirect("\\HouseOfCards\\showAdvertise.jsp?id="+ newAdvertise.getId());}

	}
	
	public void addAdvertise(Advertise advertise) {
		allAdvertises.add(advertise);
		
		
	}

    protected void editAdvertise(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	Advertise newAdvertise = SelectFromDB.getCertainAdvertiseInfo(id);
		newAdvertise.setTitle(request.getParameter("title"));
		newAdvertise.setDescription(request.getParameter("description"));
		newAdvertise.setStatus(request.getParameter("status"));
		newAdvertise.setForWhat(request.getParameter("forWhat"));
		newAdvertise.setPrice(Double.parseDouble(request.getParameter("price")));

		Part filePart1 = request.getPart("mainPhoto");
		InputStream fileContent1 = filePart1.getInputStream();
		newAdvertise.setMainPhoto(fileContent1);

		List<Part> fileParts = request.getParts().stream().filter(part -> "otherPhotos".equals(part.getName()))
				.collect(Collectors.toList());
		for (Part filePart : fileParts) {
			InputStream fileContent = filePart.getInputStream();
			newAdvertise.addPhoto(fileContent);
		}
		UpdateInDB.UpdateHouse(newAdvertise);
		// send notification
		MainController mainController = (MainController) request.getServletContext().getAttribute("mainController");
		mainController.sendPreferenceNotification(newAdvertise, request.getRequestURL().toString());
		response.sendRedirect("\\HouseOfCards\\showAdvertise.jsp?id="+ newAdvertise.getId());
    }
    
    public void addRate(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	double rate = Double.parseDouble(request.getParameter("rateValue"));
    	Advertise advertise = SelectFromDB.getCertainAdvertiseInfo(id);
    	advertise.setRate((advertise.getRate() + rate/5));
    	UpdateInDB.UpdateHouse(advertise);
    	response.sendRedirect("\\HouseOfCards\\showAdvertise.jsp?id="+ advertise.getId());
    }
    
}
