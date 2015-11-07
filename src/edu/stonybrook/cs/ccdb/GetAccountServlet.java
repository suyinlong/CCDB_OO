package edu.stonybrook.cs.ccdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		Connection conn;
		Statement stmt;
		ResultSet rs;


		String query = request.getParameter("Query");
		String sql;
		sql = Configuration.QUERY[Integer.parseInt(query)-1];
		
		
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
			
			PrintHTMLCommonBegin( request, response );
			
			int columnCnt = rs.getMetaData().getColumnCount();
			PrintWriter out = response.getWriter();
			
			// Print the query
			// TODO: Try to use English to print the sql query.
			//out.println( "<b>SQL Query</b>: " + sql );
			
			out.println( "<b>Result</b>" );
			out.println( "<table border='1'>" );
			
			// Print attributes
			out.println( "<thead> <TR>" );
			for( int i = 1; i <= columnCnt; ++i )
			{
				String label = rs.getMetaData().getColumnLabel( i );
                out.println( "<TD><b>"+ label +"</b></TD>" );
			}
			out.println( "</thead> </TR>" );
			
			// Print results
			while (rs.next())
			{
				out.println( "<TR align='left'>" );
				for( int i = 1; i <= columnCnt; ++i )
				{
	                out.println( "<TD>"+ rs.getString(i) +"</TD>" );
				}
				out.println( "</TR>" );
			}

			out.println( "</table>" );
			
			PrintHTMLCommonEnd( request, response );
		        
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}
	}
	
	private void PrintHTMLCommonBegin( HttpServletRequest request, HttpServletResponse response )
					throws IOException
	{
		PrintWriter out = response.getWriter();
        out.println (
                  "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                      "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                  "<html> \n" +
                    "<head> \n" +
                      "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
                        "charset=ISO-8859-1\"> \n" +
                      "<title> Query Results  </title> \n" +
                    "</head> \n" +
                    "<body> \n" +
                    "<center><a href='Query1.jsp'>Home</a> </center><br><br>"
                );
	}

	private void PrintHTMLCommonEnd( HttpServletRequest request, HttpServletResponse response )
					throws IOException
	{
		PrintWriter out = response.getWriter();
	    out.println (
	              "</body> \n" +
	              "</html>" 
	            );
	}
}
