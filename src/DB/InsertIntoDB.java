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

import Models.Comment;
import Models.House;
import Models.Normal;
import Models.Notifications;

public class InsertIntoDB {
	/* Add/Insert **/
	//Checked
//	public static void addUser(Normal user) {
//		Connection conn = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException ex) {
//		}
//		try {
//			conn = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
//
//			String query = "INSERT INTO users ( username, name , user_type, email , phone , pass ,pic  ) VALUES (?,?,?,?,?,?,?);";
//			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//			pstmt.setString(1, user.getUserName());
//			pstmt.setString(2, user.getName());
//			pstmt.setString(3, user.getType());
//			pstmt.setString(4, user.getEmail());
//			pstmt.setString(5, user.getPhone());
//			pstmt.setString(6, user.getPassword());
//			/*File image = new File(path);*/
//			FileInputStream fis = new FileInputStream ( (user.getPic()).toString() );
//			//pstmt.setBlob(7, fis);
//			pstmt.setBinaryStream (7, fis, (int) user.getPic().length );
//
//			int executeUpdate = pstmt.executeUpdate();
//			if(executeUpdate == 1 )
//				System.out.println("Successfully had been add");
//			conn.close();
//		} catch (SQLException | FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	/** Missing house photo **/
	//Checked
	///add address and title 
	public static int addHouse(House house) {
		Connection conn = null;
		int id = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=shosho&characterEncoding=utf8");

			Statement stmt = conn.createStatement();
			String query = "INSERT INTO property (size,numOfFloor,rate,username,description,forWhat,status,type) VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, house.getSize());
			pstmt.setInt(2, house.getNumOfFloors());
			pstmt.setDouble(3, house.getRate());
			pstmt.setString(4, house.getOwner().getUserName());
			pstmt.setString(5, house.getDescription());
			pstmt.setString(6, house.getForWhat());
			pstmt.setString(7, house.getStatus());
			pstmt.setString(8, house.getType());

			int executeUpdate = pstmt.executeUpdate();
			if(executeUpdate == 1 )
				System.out.println("Successfully had been add");
			query = "SELECT id  from property where size = " + house.getSize() + " and numOfFloor = "
					+ house.getNumOfFloors() + " and rate = " + house.getRate() + " and username = '"
					+ house.getOwner().getUserName() + "' and description = '" + house.getDescription()
					+ "' and forWhat = '" + house.getForWhat() + "' and status = '" + house.getStatus()
					+ "' and type = '" + house.getType() + "' ;";

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
	public static void addNotification(Normal user, Notifications noitify) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");

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
	
	public static void addComment(Normal user, House house, Comment comment) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");

			String query = "INSERT INTO comment (link,body, type , username ,id ) VALUES(?,?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			//pstmt.setString(1, comment.getLink());
			pstmt.setString(2, comment.getBody());
			/** Convert Date **/
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(comment.getDate().toString());
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			pstmt.setDate(3, sqlDate);
			pstmt.setString(4, user.getUserName());
			pstmt.setInt(5, house.getId());
			int executeUpdate = pstmt.executeUpdate();
			if(executeUpdate == 1 )
				System.out.println("Successfully had been add");
			conn.close();

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}

	}


}
