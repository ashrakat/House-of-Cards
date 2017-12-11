package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Models.Advertise;
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
					"jdbc:mysql://localhost:3306/ai?" + "user=root&password=shosho&characterEncoding=utf8");
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
			if(executeUpdate == 1 )
				System.out.println("Updated Successfull");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void UpdateHouse( Advertise  advertise) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ai?" + "user=root&password=shosho&characterEncoding=utf8");

			String query = "UPDATE property set size =?,numOfFloor=?,rate=?,username=?,description=?,forWhat=?,status=?,type=?,price=?,title=?,address=? where id = "
					+ advertise.getId() + " ;";
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, advertise.getSize());
			pstmt.setInt(2, advertise.getNumOfFloors());
			pstmt.setDouble(3, advertise.getRate());
			pstmt.setString(4, advertise.getOwner().getUserName());
			pstmt.setString(5, advertise.getDescription());
			pstmt.setString(6, advertise.getForWhat());
			pstmt.setString(7, advertise.getStatus());
			pstmt.setString(8, advertise.getType());
			pstmt.setDouble(9, advertise.getPrice());
			pstmt.setString(10, advertise.getTitle());
			pstmt.setString(11, advertise.getAddress());


			int executeUpdate = pstmt.executeUpdate();
			if (executeUpdate == 1)
				System.out.println("Update Success");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


}
