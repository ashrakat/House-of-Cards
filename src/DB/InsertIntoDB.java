package DB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import Models.*;

public class InsertIntoDB {
	/* Add/Insert **/
	//Checked
	public static void addUser(User user) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ai?" + "user=root&password=shosho&characterEncoding=utf8");

			String query = "INSERT INTO users ( username, name , user_type, email , phone , pass) VALUES (?,?,?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getType());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getPassword());
			/*File image = new File(path);*/
			//FileInputStream fis = new FileInputStream ( user.getPic() );
			//pstmt.setBlob(7, fis);
			//pstmt.setBinaryStream (7, fis, (int) user.getPic().length() );

			int executeUpdate = pstmt.executeUpdate();
			if(executeUpdate == 1 )
				System.out.println("Successfully had been add");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** Missing Advertise photo **/
	//Checked
	///add address and title 
	public static int addAdvertise(Advertise Advertise) {
		Connection conn = null;
		int id = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ai?" + "user=root&password=shosho&characterEncoding=utf8");

			Statement stmt = conn.createStatement();
			String query = "INSERT INTO property (size,numOfFloor,rate,username,price,title,address,description,forWhat,status,type) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, Advertise.getSize());
			pstmt.setInt(2, Advertise.getNumOfFloors());
			pstmt.setDouble(3, Advertise.getRate());
			pstmt.setString(4, Advertise.getOwner().getUserName());
			pstmt.setDouble(5, Advertise.getPrice());
			pstmt.setString(6, Advertise.getTitle());
			pstmt.setString(7, Advertise.getAddress());
			pstmt.setString(8, Advertise.getDescription());
			pstmt.setString(9, Advertise.getForWhat());
			pstmt.setString(10, Advertise.getStatus());
			pstmt.setString(11, Advertise.getType());

			int executeUpdate = pstmt.executeUpdate();
			if(executeUpdate == 1 )
				System.out.println("Successfully had been add");
			query = "SELECT id  from property where size = " + Advertise.getSize() + " and numOfFloor = "
					+ Advertise.getNumOfFloors() + " and rate = " + Advertise.getRate() + " and username = '"
					+ Advertise.getOwner().getUserName() + "' and description = '" + Advertise.getDescription()
					+ "' and forWhat = '" + Advertise.getForWhat() + "' and status = '" + Advertise.getStatus()
					+ "' and type = '" + Advertise.getType() + "' and title = '"+Advertise.getTitle()
					+"' and address = '"+Advertise.getAddress()+"' and price = " + Advertise.getPrice() +" ;";

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	//Checked
	public static void addNotification(User user, Notifications noitify) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ai?" + "user=root&password=shosho&characterEncoding=utf8");

			String query = "INSERT INTO notfication (link,body, type , username) VALUES(?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, noitify.getLink());
			pstmt.setString(2, noitify.getBody());
			pstmt.setString(3, noitify.getType());
			pstmt.setString(4, user.getUserName());
			int executeUpdate = pstmt.executeUpdate();
			if(executeUpdate == 1 )
				System.out.println("Successfully had been add");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addComment(User user, Advertise Advertise, Comment comment) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ai?" + "user=root&password=shosho&characterEncoding=utf8");

			String query = "INSERT INTO comment (body, type , username ,id ) VALUES(?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, comment.getBody());
			/** Convert Date **/
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(comment.getDate().toString());
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			pstmt.setDate(2, sqlDate);
			pstmt.setString(3, user.getUserName());
			pstmt.setInt(4, Advertise.getId());
			int executeUpdate = pstmt.executeUpdate();
			if(executeUpdate == 1 )
				System.out.println("Successfully had been add");
			conn.close();

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}

	}


}
