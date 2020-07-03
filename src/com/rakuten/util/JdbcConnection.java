package com.rakuten.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {

	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Class.forName("com.mysql.jdbc.Driver").newInstance();

//		 String url = "jdbc:mysql://dongtze123.xicp.net:45401/rakuten?user=root&password=admin";
//		String url = "jdbc:mysql://47.96.28.113:45401/rakuten?user=root&password=admin";
//		String url = "jdbc:mysql://localhost:3306/rakuten?user=root&password=admin";
		String url = "jdbc:mysql://192.168.1.5:3306/rakuten?user=root&password=tanluzhe1218";
//		String url = "jdbc:mysql://192.168.1.106:3306/rakuten?user=root&password=admin";
		Connection con = DriverManager.getConnection(url);
		con.setAutoCommit(false);
		return con;

	}

}
