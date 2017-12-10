import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import Models.*;

public class DB {
	/* Add/Insert **/
	/** Missing user photo */
	//Checked
	public void addUser(Normal user) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");

			Statement stmt = conn.createStatement();
			String query = "INSERT INTO users ( username, name , user_type, email , phone , pass ,pic  ) VALUES (?,?,?,?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getType());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getPassword());
			/*File image = new File(path);*/
			FileInputStream fis = new FileInputStream ( user.getPic() );
			//pstmt.setBlob(7, fis);
			pstmt.setBinaryStream (7, fis, (int) user.getPic().length() );

			int executeUpdate = pstmt.executeUpdate();
			conn.close();
		} catch (SQLException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/** Missing house photo **/
	//Checked
	public static int addHouse(House house) {
		Connection conn = null;
		int id = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");

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

			Statement stmt = conn.createStatement();
			String query = "INSERT INTO notfication (link,body, type , username) VALUES(?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, noitify.getLink());
			pstmt.setString(2, noitify.getBody());
			pstmt.setString(3, noitify.getType());
			pstmt.setString(4, user.getUserName());
			int executeUpdate = pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addComment(Normal user, House house, Comment comment) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");

			Statement stmt = conn.createStatement();
			String query = "INSERT INTO comment (link,body, type , username ,id ) VALUES(?,?,?,?.?);";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, comment.getLink());
			pstmt.setString(2, comment.getBody());
			/** Convert Date **/
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(comment.getDate().toString());
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			pstmt.setDate(3, sqlDate);
			pstmt.setString(4, user.getUserName());
			pstmt.setInt(5, house.getId());
			int executeUpdate = pstmt.executeUpdate();
			conn.close();

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}

	}

	/*Select ***/
	//Checked
	public  HashMap<String, String> allUserName() {
		HashMap<String, String> allUsers = new HashMap<String, String>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "SELECT  username , pass FROM users ;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				allUsers.put(username, pass);
				System.out.println("UserName : " + username + " , PassWord: " + pass);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allUsers;
	}
	//Checked
	public static ArrayList<House> allHouses() {
		ArrayList<House> houses = new ArrayList<House>();
		String Name = "";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM property;";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				House house = new House();
				house.setId(rs.getInt("id"));
				house.setSize(rs.getInt("size"));
				house.setNumOfFloors(rs.getInt("numOfFloor"));
				house.setRate(rs.getDouble("rate"));
				Name = rs.getString("username");
				house.setOwner(getCertianUserInfo(Name));
				house.setDescription(rs.getString("description"));
				house.setForWhat(rs.getString("forWhat"));
				house.setStatus(rs.getString("status"));
				house.setType("type");

				houses.add(house);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return houses;
	}
	//Checked
	public static ArrayList<House> allUserHouses(User user) {

		ArrayList<House> houses = new ArrayList<House>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM property where username = '" + user.getUserName() + "' ;";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				House house = new House();
				house.setId(rs.getInt("id"));
				house.setSize(rs.getInt("size"));
				house.setNumOfFloors(rs.getInt("numOfFloor"));
				house.setRate(rs.getDouble("rate"));
				house.setOwner(user);
				house.setDescription(rs.getString("description"));
				house.setForWhat(rs.getString("forWhat"));
				house.setStatus(rs.getString("status"));
				house.setType("type");

				houses.add(house);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return houses;
	}

	public ArrayList<Comment> allHouseComment(House house) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		Connection conn = null;
		String Name = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM comment where id = " + house.getId() + " ;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setLink(rs.getString("link"));
				comment.setBody(rs.getString("body"));
				// java.sql.Date sqlDate = rs.getDate("date");
				java.util.Date newDate = rs.getTimestamp("date");
				comment.setDate(newDate);
				Name = rs.getString("username");
				comment.setOwner(getCertianUserInfo(Name));
				comments.add(comment);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

	public ArrayList<Comment> allUserComment(User user) {

		ArrayList<Comment> comments = new ArrayList<Comment>();
		Connection conn = null;
		String Name = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM comment where username = '" + user.getUserName() + "' ;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setLink(rs.getString("link"));
				comment.setBody(rs.getString("body"));
				// java.sql.Date sqlDate = rs.getDate("date");
				java.util.Date newDate = rs.getTimestamp("date");
				comment.setDate(newDate);
				comment.setOwner(user);
				comments.add(comment);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}
	//Checked
	public static ArrayList<Notifications> allUserNotfication(User user) {
		ArrayList<Notifications> notifications = new ArrayList<Notifications>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM notfication where username = '" + user.getUserName() + "' ;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Notifications notification = new Notifications();
				notification.setLink(rs.getString("link"));
				notification.setBody(rs.getString("body"));
				notification.setType(rs.getString("type"));
				notifications.add(notification);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notifications;
	}

	/** Missing retrieve photo **/
	//Checked
	public static User getCertianUserInfo(String userName) {
		User user = new Normal();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM users where username = '" + userName + "' ;";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String Name =rs.getString("username");
				System.out.println("Naaaaaaaame :" + Name);
				user.setUserName(Name);
				user.setName(rs.getString("name"));
				user.setType(rs.getString("user_type"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setPassword(rs.getString("pass"));
				InputStream imgStream = rs.getBinaryStream("pic"); 

				// user.setPic(pic);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	/** Check with your team if it's possbile case */
	public void getCertainHouseInfo() {

	}

	/* Update **/
	public void UpdateUser(User user) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "UPDATE users set name=?, user_type=?,email=?,phone=?,pass=? where username = '"
					+ user.getUserName() + "' ;";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getType());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getPassword());

			// pstmt.setBlo ( 7, user.getPic());

			int executeUpdate = pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void UpdateHouse(House house) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");

			Statement stmt = conn.createStatement();
			String query = "UPDATE property set size =?,numOfFloor=?,rate=?,username=?,description=?,forWhat=?,status=?,type=? where id = "
					+ house.getId() + " ;";
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
			if (executeUpdate == 1)
				System.out.println("Update Success");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* Delete **/
	public void DeleteComment(House house, User user) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "DELETE  FROM comment  where username = '" + user.getUserName() + "' and id = "
					+ house.getId() + " ;";
			int executeUpdate = stmt.executeUpdate(query);
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void main (String[] args)
	{
		User Newuser =  getCertianUserInfo("NoorMohamed");
		System.out.println("Normal : "+Newuser.getName()+" , "+Newuser.getEmail()+" , "+Newuser.getPhone());
		
		/*House house = new House () ; 
		house.setSize(900);
		house.setNumOfFloors(4);
		house.setRate(9500.5);
		house.setOwner(Newuser);
		house.setDescription("New mini  Villa with only one garden:(" );
		house.setForWhat("Rent");
		house.setStatus("Finish");
		house.setType("Studio");		
		
		house.setId(addHouse(house));
		System.out.println(house.getId());
		*/
		
		
		ArrayList<Notifications> notifies  = allUserNotfication(Newuser); 
		System.out.println("Sizeeeeee : " + notifies.size());
		System.out.println(notifies.get(0).getBody());
		
	}
}
