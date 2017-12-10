package DataBase;
import Models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteFromDB {
	
	/* Delete **/
	public static void DeleteComment(Advertise advertise, User user) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ia?" + "user=root&password=noor92&characterEncoding=utf8");
			Statement stmt = conn.createStatement();
			String query = "DELETE FROM comment  where username = '" + user.getUserName() + "' and id = "
					+ advertise.getId() + " ;";
			int executeUpdate = stmt.executeUpdate(query);
			if(executeUpdate == 1 )
				System.out.println("Deleted Successful :D");
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

}
