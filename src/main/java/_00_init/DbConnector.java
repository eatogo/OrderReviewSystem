package _00_init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	String driverName = "com.mysql.jdbc.Driver";
	String dbUrl = "jdbc:mysql://localhost:3306/mysql?useSSL=true&useUnicode=yes&characterEncoding=UTF8";
	private Connection conn;

	public Connection connect(String USER, String PASSWORD) {
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, USER, PASSWORD);
			return conn;
		} catch (SQLException e) {
			System.out.println("資料庫連線失敗");
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("找不到JDBC Driver");
			return null;
		}
	}

}
