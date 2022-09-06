package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	/* truong */
//	 private static String databaseName = "Training_DevNho";
//	 private static String username = "sa";
//	 private static String password = "12345678";
//	 private static String serverName = "INTERN-DEV8:1434";
//	private static String databaseName = "TEAM3-DBTEST";
//	private static String username = "sa";
//	private static String password = "It123456!";
//	private static String serverName = "10.128.58.8";

	/* tu anh */
	private static String databaseName = "Training_DevNho";
	private static String username = "sa";
	private static String password = "12345678";
	private static String serverName = "INTERN-DEV16:1433";

	/* tung */
//	private static String databaseName = "Training_DevNho";
//	private static String username = "sa";
//	private static String password = "123456789";
//	private static String serverName = "INTERN-DEV18:1433";

	private static String url = "jdbc:sqlserver://" + serverName + "; databaseName=" + databaseName;

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
