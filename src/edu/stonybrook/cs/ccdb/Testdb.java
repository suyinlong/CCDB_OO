package edu.stonybrook.cs.ccdb;

import java.sql.*;

public class Testdb {
	public static void main(String[] args) {
		Connection conn;
		Statement stmt;
		ResultSet rs;
		
		String usr = "yinlongsu";
		String pwd = "CSE532A2";
		String url = "jdbc:postgresql://127.0.0.1:5432/ccdb";
		String sql = "select * from test";
		
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		} catch (Exception e) {
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}
	}
}
