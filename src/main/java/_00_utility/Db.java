package _00_utility;

public class Db {
	public final static String URL = "jdbc:mysql://localhost:3306/eatogodb?useSSL=true&useUnicode=yes&characterEncoding=UTF8";
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String HOST = "localhost";
	public static final String DB = "eatogodb";
	public static final String USER = "root";
	public static final String PASSWORD = "password";
	public static final String URLALL = "jdbc:mysql://" + HOST + ":3306/" + DB + "?user=" + USER
			+ "&password=" + PASSWORD + "&useSSL=true&useUnicode=yes&characterEncoding=UTF-8";
}

