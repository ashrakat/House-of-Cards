package DB;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import Models.Comment;
import Models.House;
import Models.Normal;
import Models.Notifications;
import Models.User;

public class SelectFromDB {
	/*Select ***/
	//Checked
	public static HashMap<String, String> allUserName() {
		HashMap<String, String> allUsers = new HashMap<String, String>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=shosho&characterEncoding=utf8");
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
				house.setOwner(getCertainUserInfo(Name));
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

	public static ArrayList<Comment> allHouseComment(House house) {
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
				//comment.setLink(rs.getString("link"));
				comment.setBody(rs.getString("body"));
				// java.sql.Date sqlDate = rs.getDate("date");
				java.util.Date newDate = rs.getTimestamp("date");
				comment.setDate(newDate);
				Name = rs.getString("username");
				comment.setOwner(getCertainUserInfo(Name));
				comments.add(comment);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

	public static ArrayList<Comment> allUserComment(User user) {

		ArrayList<Comment> comments = new ArrayList<Comment>();
		Connection conn = null;
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
				//comment.setLink(rs.getString("link"));
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

	//Checked
	public static User getCertainUserInfo(String userName) {
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
				user.setUserName(rs.getString("username"));
				user.setName(rs.getString("name"));
				//user.setType(rs.getString("user_type"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setPassword(rs.getString("pass"));
				InputStream imgStream = rs.getBinaryStream("pic"); 
				OutputStream out = null;
				out = new FileOutputStream(new File("F:\\Anime\\fromDb.png"));
				int c = 0;
                                //write the contents from the input stream to the output stream
				c = imgStream.read() ; 
				while (c != -1) {
					out.write(c);
					c = imgStream.read();
				}
				// user.setPic(pic);
				 if(out != null){
						out.close();
					}
			}
			conn.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return user;
	}
	
	/** Missing retrieve photos */
	/** Check with your team if it's possible case */
	public static House getCertainHouseInfo( int id ) {
		House house = new House();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM property where id = " + id + " ;";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				house.setId(rs.getInt("id"));
				house.setSize(rs.getInt("size"));
				house.setNumOfFloors(rs.getInt("numOfFloor"));
				house.setRate(rs.getDouble("rate"));
				String Name = rs.getString("username");
				house.setOwner(getCertainUserInfo(Name));
				house.setDescription(rs.getString("description"));
				house.setForWhat(rs.getString("forWhat"));
				house.setStatus(rs.getString("status"));
				house.setType("type");

				// house.setPic(pic);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return house;
	}


}
