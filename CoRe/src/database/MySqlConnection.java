package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

	protected static Connection getConnection () throws SQLException{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "root00");
		return con;
	}
}