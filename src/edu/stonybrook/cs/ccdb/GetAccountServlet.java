package edu.stonybrook.cs.ccdb;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

//import javax.servlet.jsp.jstl.sql.Result;
//import javax.servlet.jsp.jstl.sql.ResultSupport;
import java.sql.ResultSet;
/**
 * Servlet implementation class GetAccountServlet
 */
@WebServlet(name="GetAccountServlet", urlPatterns={"/GetAccountServlet"})
public class GetAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Account> accountList = new ArrayList<>();
		Connection conn;
		Statement stmt;
		ResultSet rs;
		
		Account account = null;
		
		String sql = "select * from \"Account\"";
		//String requestStr = IOUtils.toString(request.getInputStream());
		//String sql = request.toString();
		System.out.println(request.getQueryString());
		
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
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				account = new Account();
				account.SetNumber( rs.getString(1));
				account.SetBalance( rs.getInt(2));
				account.SetLimit( rs.getInt(3));
				accountList.add(account);
			}

		    request.setAttribute("result", accountList);
		    RequestDispatcher reqDispatcher = request.getRequestDispatcher("/AccountView.jsp");
		    reqDispatcher.forward(request, response);
		        
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}
	}
}
