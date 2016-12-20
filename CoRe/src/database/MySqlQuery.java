package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * このクラスは端末でデータを扱うためのクラスです。
 * @author nikami
 * ver.1.0
 */
public class MySqlQuery {
	//private String TABLE = "テーブルの名前";
	private Connection con = null;

	/**
	 * データベースサーバに接続を行います。
	 */
	public MySqlQuery() throws  SQLException {
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
	}

	/**
	 *
	 * @throws SQLException
	 */
	private void close() throws SQLException{
		con.close();
	}

	/**
	 * データベースサーバにsql文を送信するためのメソッドです。
	 * @param sql
	 * データベースサーバに送信するsql文
	 * @return
	 * データベースサーバから返信された結果
	 * @throws SQLException
	 * このメソッドはSQLExceptionが発生する可能性があります。
	 */
	private ResultSet myExecuteQuery (String sql) throws SQLException {
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		return rs;
	}


	/**
	 * データベースサーバにsql文を送信し、更新を行うためのメソッドです。
	 * @param sql
	 * sql文
	 * @return
	 * 更新されたデータ数
	 * @throws SQLException
	 * 例外が発生する可能性があります
	 */
	private int  myExecuteUpdate(String sql) throws SQLException {
		Statement stm = con.createStatement();
		int num = stm.executeUpdate(sql);
		return num;
	}


	/**
	 * 混雑状況データを送信するメソッドです。
	 * @param areaNum エリア番号
	 * @param data 日付
	 */
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


	/**
	 * 最新の混雑状況データを取得するメソッドです。<br>
	 * 指定されたエリア番号の混雑状況データを返却します。
	 * @param areaNum エリア番号
	 * @return 混雑状況データ
	 */
	public int dbNewData (int areaNum ) throws SQLException {
		String num = Integer.toString(areaNum);
		String sql = "select MAX(Time), AreaCode, Congestion "
				+ "from onedaytable where AreaCode = " + num
				+ "group by areaCode;";
		ResultSet result = myExecuteQuery(sql);
		return 77;
	}

	/**
	 * 過去4週間の現在時刻の混雑状況データを取得するメソッドです。
	 * @param areaNum エリア番号
	 * @return 過去の混雑状況データ
	 */
	public int dbPastData(int areaNum) {
		return 77;
	}

	/**
	 * 混雑状況データのグラフ用データを取得するメソッドです。
	 * 指定された条件のグラフ用データを配列として返却します。
	 * @param year 年
	 * @param Quarter クォータ
	 * @param day 曜日
	 * @param areaNum エリア番号
	 * @return グラフ用配列データ
	 */
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

	/**
	 * ログインIDからパスワードのハッシュ値を取得するメソッドです。
	 * @param id ログインID
	 * @return パスワードのハッシュ値
	 */
	public String getKey(String id) {
		return "sdfsdfsdfsdfsdfsdfsdfsdfsdfsd";
	}

	/**
	 * 一日の終わりに呼び出すメソッドです。
	 * 一日のデータを編集し、データベースに格納します。
	 */
	public void insertPreData() {
		return;
	}


	public void finalize() {
		try {
			con.close();
			System.out.println("終了");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
