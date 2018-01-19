package DB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Models.*;

public class InsertIntoDB {
	/* Add/Insert **/
	// Checked
	public static boolean addUser(User user) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=shosho&characterEncoding=utf8");

			String query = "INSERT INTO users ( username, name , user_type, email , phone , pass , pic) VALUES (?,?,?,?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getType());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getPassword());
			/* File image = new File(path); */
			// FileInputStream fis = new FileInputStream ( user.getPic() );
			// pstmt.setBlob(7, user.getPic());
			pstmt.setBinaryStream(7, user.getPic());

			int executeUpdate = pstmt.executeUpdate();
			if (executeUpdate == 1)
				return true; 
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	// Checked
	public static int addAdvertise(Advertise Advertise) {
		Connection conn = null;
		int id = -2;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=shosho&characterEncoding=utf8");

			Statement stmt = conn.createStatement();
			String query = "INSERT INTO property (size,numOfFloor,rate,username,price,title,address,description,forWhat,status,type,pic,closed,suspend) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
			pstmt.setBinaryStream(12, Advertise.getMainPhoto());
			pstmt.setBoolean(13, Advertise.isClose());
			pstmt.setBoolean(14, Advertise.isSusbinded());

			int executeUpdate = pstmt.executeUpdate();
			if (executeUpdate == 1)
				System.out.println("Successfully had been add");
			query = "SELECT id  from property where size = " + Advertise.getSize() + " and numOfFloor = "
					+ Advertise.getNumOfFloors() + " and rate = " + Advertise.getRate() + " and username = '"
					+ Advertise.getOwner().getUserName() + "' and description = '" + Advertise.getDescription()
					+ "' and forWhat = '" + Advertise.getForWhat() + "' and status = '" + Advertise.getStatus()
					+ "' and type = '" + Advertise.getType() + "' and title = '" + Advertise.getTitle()
					+ "' and address = '" + Advertise.getAddress() + "' and price = " + Advertise.getPrice() + " ;";

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getInt("id");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		addAdvertisePhotos(id, Advertise.getOtherPhotos());
		return id;
	}
	//Checked
	public static void addAdvertisePhotos(int id, ArrayList<InputStream> photos) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=shosho&characterEncoding=utf8");
			for(int i =0 ; i < photos.size() ; i ++){
				String query = "INSERT INTO picproperty (pic,id) VALUES (?,?);";
				PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				pstmt.setBinaryStream(1,photos.get(i));
				pstmt.setInt(2,id);
				
			   pstmt.executeUpdate();
			
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Checked
	public static void addNotification(User user, Notification noitify) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=shosho&characterEncoding=utf8");

			String query = "INSERT INTO notfication (link,body,type,username,readed) VALUES(?,?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, noitify.getLink());
			pstmt.setString(2, noitify.getBody());
			pstmt.setString(3, noitify.getType().toString());
			pstmt.setString(4, user.getUserName());
			pstmt.setBoolean(5, noitify.isRead());

			int executeUpdate = pstmt.executeUpdate();
			if (executeUpdate == 1)
				System.out.println("Successfully had been add");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Checked
	public static void addComment(User user, Advertise Advertise, Comment comment) {
		System.out.println("id " + Advertise.getId() + " username : " + user.getUserName()  );
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=shosho&characterEncoding=utf8");
			String query = "INSERT INTO comment (body,date,username,id) VALUES(?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, comment.getBody());
			/** Convert Date **/
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String s = dateFormat.format(comment.getDate());
			java.util.Date myDate = dateFormat.parse( s ); 
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			pstmt.setDate(2, sqlDate);
			
			pstmt.setString(3, user.getUserName());
			pstmt.setInt(4, Advertise.getId());
			int executeUpdate = pstmt.executeUpdate();
			if (executeUpdate == 1)
				System.out.println("Successfully had been add");
			else 
				System.out.println("can't inserted ");
			conn.close();

		} catch (SQLException | ParseException  e) {
			e.printStackTrace();
		}

	}
	//
	public static void addPreference (Preference preference , User user)	{
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=shosho&characterEncoding=utf8");
			String query = "INSERT INTO preference(type,statuse,size_min,size_max,price_max,username,price_min,deleted) VALUES(?,?,?,?,?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,preference.getType());
			pstmt.setString(2, preference.getStatuse());
			pstmt.setInt(3,preference.getSize().min);
			pstmt.setLong(4, preference.getSize().max);
			pstmt.setLong(5, preference.getPrice().max);
			pstmt.setString(6, user.getUserName());
			pstmt.setInt(7, preference.getPrice().min);
			pstmt.setBoolean(8, preference.isDelete());
			int executeUpdate = pstmt.executeUpdate();
			if (executeUpdate == 1)
				System.out.println("Successfully had been add");
			else 
				System.out.println("can't inserted ");
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
