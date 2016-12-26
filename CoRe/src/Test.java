import java.sql.SQLException;

public class Test {
	public static void main(String args[]) {
		try {
			database.MySqlQuery msq = new database.MySqlQuery();
			String result = msq.mySqlTest();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
