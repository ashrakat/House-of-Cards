package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Models.House;
import Models.User;

public class UdateInDB {
	/* Update **/
	public static void UpdateUser(User user) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=shosho&characterEncoding=utf8");
			String query = "UPDATE users set name=?, user_type=?,email=?,phone=?,pass=? where username = '"
					+ user.getUserName() + "' ;";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getName());
			//pstmt.setString(2, user.getType());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getPassword());

			// pstmt.setBlo ( 7, user.getPic());

			int executeUpdate = pstmt.executeUpdate();
			if(executeUpdate == 1 )
				System.out.println("Updated Successfull");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void UpdateHouse(House house) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");

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


}
