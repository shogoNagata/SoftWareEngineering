package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlSelectTest {

	public static void printAllTable(Connection con, String tablename) throws SQLException {
		Statement stm = null;
		ResultSet result = null;
		try {
			//sqlの操作
			stm = con.createStatement();
			String sql = "select * from " + tablename;//sql文の作成
			result = stm.executeQuery(sql);//問い合わせ

			//結果の列の型、プロパティに関する情報の取得に使用
			ResultSetMetaData metadata = result.getMetaData();
			int columnCount = metadata.getColumnCount();//列の数を取得
			String columnName[] = new String[columnCount];//名前を取得
			while(result.next()){
			String record = "";//レコードを格納する行列
				//レコードを格納する
				for (int i = 0; i < columnCount; i++) {
					columnName[i] = metadata.getColumnName(i+1);
					record = record + result.getString(columnName[i]) + " ";
				}
				System.out.println(record);//表示
			}
		} catch (SQLException e) {
			System.out.println("データベース参照中にエラーが発生しました");
			//throw new SQLException();
		} finally {
			if(stm != null)
			stm.close();
			if (result !=null )
			result.close();
		}
	}

	public static void main(String args[]){
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://222.229.69.51:3306/CoRe", "core", "ktechkut");
			System.out.println("mysqlに接続成功");
			// Statement stm = con.createStatement();
	        // String sql = "INSERT INTO user VALUES ('gggg', 'sdf', '434', '5');";
	         //stm.executeUpdate(sql);
			printAllTable(con, "test");
		} catch (SQLException e) {
			System.out.println("sqlアクセスでエラー発生");
			e.printStackTrace();
		} finally {
			if( con != null) {
				try {
					con.close();
					System.out.println("mysqlをクローズ");
				} catch (SQLException e) {
					System.out.println("データベースのクローズに失敗");
					e.printStackTrace();
				}
			}
		}
	}


}
