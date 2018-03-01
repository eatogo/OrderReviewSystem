package _00_init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUserData {
	static Connection conn = null;
	static Statement stmt = null;
	static String url = null;
	static File sqlFile = null;
	static BufferedReader br = null;
	static String dbUsername = "root";
	static String dbPassword = "password";
	static String USE_EATOGODB_SQL = "USE `eatogodb`;";

	public static void main(String[] args) {
		try {
			conn = new DbConnector().connect(dbUsername, dbPassword);
			stmt = conn.createStatement();
			stmt.executeQuery(USE_EATOGODB_SQL);
			System.out.println("開始建立固定資料");
			executeSqlFromFile("UserData.sql");
			System.out.println("建立固定資料成功");
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQL問題，建立固定資料失敗");
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("關閉stmt錯誤");
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("關閉conn錯誤");
					e.printStackTrace();
				}
		}

	}

	public static void executeSqlFromFile(String fileName) throws SQLException {
		stmt = conn.createStatement();
		url = CreateUserData.class.getClass().getResource("/").getPath();
		sqlFile = new File(url, fileName);
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(sqlFile), "UTF8"));
			String sql;
			while ((sql = br.readLine()) != null) {
				stmt.executeUpdate(sql);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println("檔案編碼錯誤" + fileName);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("找不到檔案：" + fileName);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("讀取檔案錯誤：" + fileName);
			e.printStackTrace();
		}
		stmt.close();
	}

}