package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcsessTest {
	public static void main(String args[]){
		Connection con = null; //データベースの接続を表現
		try {
			//データベースに接続("データベース","ユーザ名","パスワード")
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "root00");
			System.out.println("mysqlに接続成功");
		} catch (SQLException e) {
			System.out.println("sqlアクセスでエラー発生");
			e.printStackTrace();
		} finally {
			//接続がnull出ないならクローズ
			if( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println("mysqlのクローズに失敗");
					e.printStackTrace();
				}
			}
		}
	}
}
