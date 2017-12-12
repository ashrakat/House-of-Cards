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
import Models.Advertise;
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
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "SELECT  username , pass FROM users ;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				allUsers.put(username, pass);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allUsers;
	}
	//Checked
	public static ArrayList<Advertise> allAdvertises() {
		ArrayList<Advertise> advertises = new ArrayList<Advertise>();
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
				Advertise advertise = new Advertise();
				advertise.setId(rs.getInt("id"));
				advertise.setSize(rs.getInt("size"));
				advertise.setNumOfFloors(rs.getInt("numOfFloor"));
				advertise.setRate(rs.getDouble("rate"));
				Name = rs.getString("username");
				advertise.setOwner((Normal)getCertainUserInfo(Name));
				advertise.setDescription(rs.getString("description"));
				advertise.setForWhat(rs.getString("forWhat"));
				advertise.setStatus(rs.getString("status"));
				advertise.setType("type");
				advertise.setPrice(rs.getDouble("price"));
				advertise.setTitle(rs.getString("title"));
				advertise.setAddress(rs.getString("address"));
				InputStream imgStream = rs.getBinaryStream("pic");
				advertise.setMainPhoto(imgStream);
				OutputStream outfile = null;
				String path = "";
					path = "C:\\Users\\norha\\Documents\\workspace-sts-3.9.1.RELEASE\\HouseOfCards\\WebContent\\"
							+ advertise.getId() + ".jpg";
					outfile = new FileOutputStream(new File(path));
					int c = 0;
					c = advertise.getMainPhoto().read();
					while (c != -1) {
						outfile.write(c);
						c = advertise.getMainPhoto().read();
					}
					if (outfile != null) 
						 outfile.close();	
				advertises.add(advertise);
			}
			conn.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		for(int i = 0 ; i < advertises.size() ; i++)
			advertises.get(i).setOtherPhotos(loadCertainAdvertisePhotos(advertises.get(i).getId()));

		return advertises;
	}
	//Checked
	public static ArrayList<Advertise> allUserAdvertises(User user) {

		ArrayList<Advertise> advertises = new ArrayList<Advertise>();
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
				Advertise advertise = new Advertise();
				advertise.setId(rs.getInt("id"));
				advertise.setSize(rs.getInt("size"));
				advertise.setNumOfFloors(rs.getInt("numOfFloor"));
				advertise.setRate(rs.getDouble("rate"));
				advertise.setOwner((Normal)user);
				advertise.setDescription(rs.getString("description"));
				advertise.setForWhat(rs.getString("forWhat"));
				advertise.setStatus(rs.getString("status"));
				advertise.setType("type");
				advertise.setPrice(rs.getDouble("price"));
				advertise.setTitle(rs.getString("title"));
				advertise.setAddress(rs.getString("address"));
				InputStream imgStream = rs.getBinaryStream("pic");
				advertise.setMainPhoto(imgStream);
				
				OutputStream outfile = null;
				String path = "";
					path = "C:\\Users\\norha\\Documents\\workspace-sts-3.9.1.RELEASE\\HouseOfCards\\WebContent\\"
							+ advertise.getId() + ".jpg";
					outfile = new FileOutputStream(new File(path));
					int c = 0;
					c = advertise.getMainPhoto().read();
					while (c != -1) {
						outfile.write(c);
						c = advertise.getMainPhoto().read();
					}
					if (outfile != null) 
						 outfile.close();
				

				advertises.add(advertise);
			}
			conn.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return advertises;
	}
	public static ArrayList<Comment> allAdvertiseComment(Advertise advertise) {
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
			String query = "SELECT * FROM comment where id = " + advertise.getId() + " ;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Comment comment = new Comment();
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
		User user = null;
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
				user = new Normal();
				user.setUserName(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setType(rs.getString("user_type"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setPassword(rs.getString("pass"));
				InputStream imgStream = rs.getBinaryStream("pic"); 
				user.setPic(imgStream);
				
				OutputStream outfile = null;
				String path = "";
					path = "C:\\Users\\norha\\Documents\\workspace-sts-3.9.1.RELEASE\\HouseOfCards\\WebContent\\"
							+ user.getUserName() + ".jpg";
					outfile = new FileOutputStream(new File(path));
					int c = 0;
					c = user.getPic().read();
					while (c != -1) {
						outfile.write(c);
						c = user.getPic().read();
					}
					if (outfile != null) {
						outfile.close();
					}
			}
			conn.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return user;
	}
	//Checked
	public static Advertise getCertainAdvertiseInfo( int id ) {
		Advertise advertise = new Advertise();
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
				advertise.setId(rs.getInt("id"));
				advertise.setSize(rs.getInt("size"));
				advertise.setNumOfFloors(rs.getInt("numOfFloor"));
				advertise.setRate(rs.getDouble("rate"));
				String Name = rs.getString("username");
				advertise.setOwner((Normal)getCertainUserInfo(Name));
				advertise.setDescription(rs.getString("description"));
				advertise.setForWhat(rs.getString("forWhat"));
				advertise.setStatus(rs.getString("status"));
				advertise.setType("type");
				advertise.setPrice(rs.getDouble("price"));
				advertise.setTitle(rs.getString("title"));
				advertise.setAddress(rs.getString("address"));
				InputStream imgStream = rs.getBinaryStream("pic");
				advertise.setMainPhoto(imgStream);
				
				OutputStream outfile = null;
				String path = "";
					path = "C:\\Users\\norha\\Documents\\workspace-sts-3.9.1.RELEASE\\HouseOfCards\\WebContent\\"
							+ advertise.getId() + ".jpg";
					outfile = new FileOutputStream(new File(path));
					int c = 0;
					c = advertise.getMainPhoto().read();
					while (c != -1) {
						outfile.write(c);
						c = advertise.getMainPhoto().read();
					}
					if (outfile != null) 
						 outfile.close();
			}
			conn.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		advertise.setOtherPhotos(loadCertainAdvertisePhotos(advertise.getId()));
		return advertise;
	}
	//Checked
	public static boolean checkUserPass (String username , String password ){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "SELECT * from users WHERE username =  '"+username+"' and pass = '"+password+"' ;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
					return true ; 
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//Checked
    public static boolean CheckemailandUser (String email , String userName ){
    	Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "SELECT * from users where email =  '"+email+"' OR username = '"+userName+"' ;";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
					return false ; 
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
    //Checked
    public static  ArrayList<InputStream> loadCertainAdvertisePhotos(int id ){
    	ArrayList<InputStream> photos = new ArrayList<InputStream>() ;
    	Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM picproperty where id = " + id + " ;";
			ResultSet rs = stmt.executeQuery(query);
			int i = 0 ;
			while (rs.next()) {
				OutputStream outfile = null;
				String path = "";
					path = "C:\\Users\\norha\\Documents\\workspace-sts-3.9.1.RELEASE\\HouseOfCards\\WebContent\\"
							+ id +"-"+ i+ ".jpg";
					InputStream imgStream = rs.getBinaryStream("pic");
					outfile = new FileOutputStream(new File(path));
					int c = 0;
					c = imgStream.read();
					while (c != -1) {
						outfile.write(c);
						c = imgStream.read();
					}
					if (outfile != null) 
						 outfile.close();
					i++;
			}
			conn.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return photos ; 
    }
    
}
