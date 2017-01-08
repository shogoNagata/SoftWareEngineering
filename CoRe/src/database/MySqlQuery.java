package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * このクラスは端末でデータを扱うためのクラスです。
 * @author nikami
 * ver.1.0
 */
public class MySqlQuery {
	//private String TABLE = "テーブルの名前";
	public Connection con = null;

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
		String sql = "select MAX(Time), AreaCode, Congestion "
				+ "from OneDayTable "
				+ "group by AreaCode;";
		ResultSet result = myExecuteQuery(sql);
		while(result.next()) {
			if(result.getInt("AreaCode") == areaNum) {
				return result.getInt("Congestion");
			}
		}
		return -1;
	}

	public int[] dbNewData () throws SQLException{
		String sql = "select MAX(Time), AreaCode, Congestion "
				+ "from OneDayTable "
				+ "group by AreaCode;";
		ResultSet result = myExecuteQuery(sql);
		result.last();
		int numOfRow = result.getRow();
		int[] data = new int[numOfRow + 1];
		result.beforeFirst();
		while(result.next()) {
			data[result.getInt("AreaCode")] = result.getInt("Congestion");
		}
		return data;
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
	public int[] dbGraphData(int year, int Quarter, String day, int areaNum)  throws SQLException  {
		int[] error = {-1};
		
		if(Quarter == 1){
			String sql = "SELECT * "
					+ "from ThiMinTable "
					+ "where "
					+ "Date > " + year + "04000000 "
					+ "and Date <= " + year + "06079999 "
					+ "and Youbi = '" + day + "' "
					+ "and AreaCode = " + areaNum + ";";
			ResultSet result = myExecuteQuery(sql);
			
			ArrayList<Integer> graphList = new ArrayList<Integer>();

			while (result.next()) {
				graphList.add(result.getInt("ConSit"));
			}

			result.close();
			int[] graph_data = new int[graphList.size()];
			for(int i = 0; i < graphList.size(); i++){
				graph_data[i] = graphList.get(i);
				System.out.println(graph_data[i]);
			}
			return graph_data;
		}else if(Quarter == 2){
			String sql = "SELECT * "
					+ "from ThiMinTable "
					+ "where "
					+ "Date > " + year + "06080000 "
					+ "and Date <= " + year + "08320000;"
					+ "and Youbi = '" + day + "' "
					+ "and AreaCode = " + areaNum + ";";
			ResultSet result = myExecuteQuery(sql);
			
			ArrayList<Integer> graphList = new ArrayList<Integer>();

			while (result.next()) {
				graphList.add(result.getInt("ConSit"));
			}

			result.close();
			int[] graph_data = new int[graphList.size()];
			for(int i = 0; i < graphList.size(); i++){
				graph_data[i] = graphList.get(i);
				System.out.println(graph_data[i]);
			}
			return graph_data;
		}else if(Quarter == 3){
			String sql = "SELECT * "
					+ "from ThiMinTable "
					+ "where "
					+ "Date > " + year + "10000000 "
					+ "and Date <= " + year + "12079999;"
					+ "and Youbi = '" + day + "' "
					+ "and AreaCode = " + areaNum + ";";
			ResultSet result = myExecuteQuery(sql);
			
			ArrayList<Integer> graphList = new ArrayList<Integer>();

			while (result.next()) {
				graphList.add(result.getInt("ConSit"));
			}

			result.close();
			int[] graph_data = new int[graphList.size()];
			for(int i = 0; i < graphList.size(); i++){
				graph_data[i] = graphList.get(i);
				System.out.println(graph_data[i]);
			}
			return graph_data;
		}else if(Quarter == 4){
			String sql = "SELECT * "
					+ "from ThiMinTable "
					+ "where "
					+ "Date > " + year + "12080000 "
					+ "and Date <= " + (year + 1) + "02300000;"
					+ "and Youbi = '" + day + "' "
					+ "and AreaCode = " + areaNum + ";";
			ResultSet result = myExecuteQuery(sql);
			
			ArrayList<Integer> graphList = new ArrayList<Integer>();

			while (result.next()) {
				graphList.add(result.getInt("ConSit"));
			}

			result.close();
			int[] graph_data = new int[graphList.size()];
			for(int i = 0; i < graphList.size(); i++){
				graph_data[i] = graphList.get(i);
				System.out.println(graph_data[i]);
			}
			return graph_data;
		}else{
			return error;
		}
	}

	public static void main (String[] args) throws Exception {
		MySqlQuery msq = new MySqlQuery();
		msq.dbGraphData(2011, 1, "火", 7);
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

	public String mySqlTest() throws SQLException {
		ResultSet result = myExecuteQuery("select * from test order by column1 asc");
		result.next();
		String str = result.getString("column1") + ":" + result.getString("column2") + "\n";
		result.last();
		str += "2:" + result.getString("column2") + "\n";
		str += "3:接続に成功しました。";
		String next = result.getString("column1");
		int nextNo = Integer.parseInt(next);
		nextNo++;
		myExecuteUpdate("INSERT INTO `CoRe`.`test` (`column1`, `column2`) VALUES ('"
				+ nextNo + "', '"
				+ nextNo + "回目のテストです。" + "');");
		return str;
	}

}
