package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlQuery {
	//private String TABLE = "テーブルの名前";
	private Connection con = null;
	public MySqlQuery() { /*throws  SQLException {
		try {
			con = MySqlConnection.getConnection();
		} catch (SQLException e) {
			System.out.println("データベース接続に失敗しました");
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ec) {
					System.out.println("データベースのクローズに失敗しました。");
				}
			}
			throw new SQLException();
		}
		*/
	}

	public void close() throws SQLException{
		con.close();
	}

	//sql文を入力することで，データの問い合わせを行う．
	public ResultSet myExecuteQuery (String sql) throws SQLException {
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		return rs;
	}


	//sql文を入力することで，レコードの挿入・更新を行う．
	public int  myExecuteUpdate(String sql) throws SQLException {
		Statement stm = con.createStatement();
		int num = stm.executeUpdate(sql);
		return num;
	}


	//カメラコードと込み具合を指定したテーブルに挿入する
	//areaNum = エリア番号、data=曜日
	public void insertNewData(int areaNum, int data ) {
	/*
	throws SQLException {
		String str1 = Integer.toString(CameraNum);
		String str2 = Integer.toString(data);
		String sql = "INSERT INTO " + TABLE +
				" VALUES (" + areaNum + "," + data + ");";
		myExecuteUpdate(sql);
		*/
	}


	//areaNum = エリア番号
	public int dbNewData (int areaNum ) {
		return 77;
	}

	//areaNum=エリア番号
	public int dbPastData(int areaNum) {
		return 77;
	}

	//year=年、Quarter=クォータ、day=曜日、areaNum=エリア番号
	public int[] dbGraphData(int year, int Quarter, String day, int areaNum) {
		int[] graph_data ={10,20,30,40,50,60,70,80,90,70,60,50,40,30,20,10,20,30,40,50,60,70};
		return graph_data;
	}

	public static void main (String[] args) throws Exception {
		MySqlQuery msq = new MySqlQuery();
		int[] data = msq.dbGraphData(1, 1, "金", 1);
		for (int x = 0; x < data.length; x++) {
			System.out.println(data[x]);
		}
	}

	//idはログインID
	public String getKey(String id) {
		return "sdfsdfsdfsdfsdfsdfsdfsdfsdfsd";
	}

	//引数は指定せずに使用してください。
	//一日の終わりに使用
	public void insertPreData() {
		return;
	}

	protected void finalize() {
		try {
			con.close();
			System.out.println("終了");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
