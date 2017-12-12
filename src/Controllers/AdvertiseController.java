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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		newAdvertise.setId(InsertIntoDB.addAdvertise(newAdvertise));
		addAdvertise(newAdvertise);
		session.setAttribute("curAd", newAdvertise);

		response.sendRedirect("\\House_of_Cards\\showAdvertise.jsp?id="+ newAdvertise.getId());

	}

	public void addAdvertise(Advertise advertise) {
		allAdvertises.add(advertise);
	}

}
