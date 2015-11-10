/****************************************************************************
	CSE532 -- Project 2
	File name: Testdb.java
	Authors: 	Yinlong Su (110461173)
				Wei-Ying Tsai (110351414)
	Brief description: for testing JDBC
****************************************************************************/

package edu.stonybrook.cs.ccdb;

import java.sql.*;

public class Testdb {
	public static void main(String[] args) {
		Connection conn;
		Statement stmt;
		ResultSet rs;
		
		//String sql = "select * from \"Account\"";
		
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		} catch (Exception e) {
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(Configuration.URL, Configuration.USR, Configuration.PWD);
			System.out.println("Success connecting server!");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(Configuration.QUERY[0]);
			int k = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= k; i++)
					System.out.print(rs.getString(i) + " ");
				System.out.println();
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
